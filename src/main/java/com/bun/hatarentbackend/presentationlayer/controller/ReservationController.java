package com.bun.hatarentbackend.presentationlayer.controller;

import com.bun.hatarentbackend.email.EmailService;
import com.bun.hatarentbackend.reservation.businesslayer.ReservationMapper;
import com.bun.hatarentbackend.reservation.businesslayer.ReservationService;
import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import com.bun.hatarentbackend.reservation.datalayer.ReservationDTO;
import com.bun.hatarentbackend.userservice.domain.User;
import com.bun.hatarentbackend.userservice.service.UserService;
import com.bun.hatarentbackend.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping()
@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReservationController {
    private final ReservationService reservationService;
    private final ReservationMapper reservationMapper;
    private final EmailService emailService;
    private final UserService userService;
    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper, EmailService emailService, UserService userService) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
        this.emailService = emailService;
        this.userService = userService;
    }

    @GetMapping("/reservation")
    public List<Reservation> findAllReservations(){
        List<Reservation> reservationList = reservationService.findAllReservations();
        log.info("Found reservations");
        return reservationList;
    }
    @GetMapping("/reservation/user/{userId}")
    public List<Reservation> findAllReservationsByUserId(@PathVariable UUID userId){
        List<Reservation> reservationList = reservationService.findAllReservationsByUserId(userId);
        log.info("Found reservations");
        return reservationList;
    }
    @GetMapping(value = "/reservation/host/{reservationId}")
    public List<Reservation>  findReservationsById(@PathVariable UUID reservationId) {
        List<Reservation> reservationList = reservationService.findAllReservationsByHostId(reservationId);
        log.info("Found reservations");
        return reservationList;
    }
    @GetMapping("/reservation/{reservationId}")
    public Reservation findReservationById(@PathVariable @NotNull UUID reservationId) {
        Optional<Reservation> reservationEntity = reservationService.findReservationById(reservationId);
        if(reservationEntity.isEmpty()) {
            log.info("item with reservationId {} not found", reservationId);
            throw new NotFoundException();
        }
        final Reservation reservation = reservationEntity.get();
        log.info("retrieved item by reservationId {}", reservation.getReservationId());

        return reservation;
    }
    @GetMapping(value = "/reservation/property/{propertyId}")
    public List<Reservation> findReservationsByPropertyId(@PathVariable @NotNull UUID propertyId) {
        List<Reservation> reservationList = reservationService.findAllReservationByPropertyUuid(propertyId);
        log.info("Found reservations");
        return reservationList;
    }

    @PostMapping( value = "/reservation",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public Reservation addReservation(@RequestBody @Valid ReservationDTO reservationDTO){
        Reservation reservationMapped = reservationMapper.reservationDTOToReservationEntity(reservationDTO);
        Reservation reservationCreated = reservationService.createReservation(reservationMapped);
        if (reservationCreated != null) {
            // Send email to host
            User guest = userService.getUserByUuid(reservationCreated.getUser());
            User host = userService.getUserByUuid(reservationCreated.getProperty().getHostUserUuid());
            String Subject = "Reservation request" + new Date().toString();
            String hostText = "You have a new reservation request from " + guest.getFirstName()+ " " + guest.getLastName()+
                    " with email: " + guest.getEmail() + " for " + reservationCreated.getProperty().getTitle() +
                    " from " + reservationCreated.getCheckInDate() + " to " + reservationCreated.getCheckOutDate() +
                    " with a total of " + reservationCreated.getTotalPrice() + "."
                    + " Please login to your account to accept or decline the request.";
            emailService.sendMessage(host.getEmail(), Subject, hostText);
            String guestText = "Thank you for your reservation request for " + reservationCreated.getProperty().getTitle() +
                    " from " + reservationCreated.getCheckInDate() + " to " + reservationCreated.getCheckOutDate() +
                    " with a total of " + reservationCreated.getTotalPrice() + "."
                    + " We will contact you shortly to confirm your reservation.";
            emailService.sendMessage(guest.getEmail(), Subject, guestText);
        }
        log.info("Reservation created");
        return reservationCreated;
    }
    @PutMapping(value = "/reservation/accept/{reservationId}",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Reservation acceptReservation(@PathVariable @NotNull UUID reservationId) {
        Reservation reservation = reservationService.acceptReservation(reservationId);
        if (reservation != null) {
            // Send email to guest
            User guest = userService.getUserByUuid(reservation.getUser());
            String Subject = "Reservation request" + new Date().toString() + " accepted";
            String guestText = "Your reservation request for " + reservation.getProperty().getTitle() +
                    " was accepted"+
                    " for " + reservation.getProperty().getTitle()+
                    " from " + reservation.getCheckInDate() + " to " + reservation.getCheckOutDate() +
                    " with a total of " + reservation.getTotalPrice() + "."
                    + " Please login to your account to check your to proceed to payment.";
            emailService.sendMessage(guest.getEmail(), Subject, guestText);
        }
        log.info("Reservation accepted");
        return reservation;
    }
    @PutMapping(value = "/reservation/decline/{reservationId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Reservation declineReservation(@PathVariable @NotNull UUID reservationId) {
        Reservation reservation = reservationService.declineReservation(reservationId);
        if (reservation != null) {
            // Send email to guest
            User guest = userService.getUserByUuid(reservation.getUser());
            String Subject = "Reservation request" + new Date().toString() + " declined";
            String guestText = "Sadly your reservation request for " + reservation.getProperty().getTitle() +
                    " was declined"+
                    " for " + reservation.getProperty().getTitle()+
                    " from " + reservation.getCheckInDate() + " to " + reservation.getCheckOutDate() +
                    " with a total of " + reservation.getTotalPrice() + "." +
                    " Please login to your account to and search for another property.";
            emailService.sendMessage(guest.getEmail(), Subject, guestText);
        }
        log.info("Reservation declined");
        return reservation;
    }
    @PutMapping(value = "/reservation/pay/{reservationId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public Reservation payReservation(@PathVariable @NotNull UUID reservationId) {
        Reservation reservation = reservationService.payReservation(reservationId);
        log.info("Reservation paid");
        return reservation;
    }
    @PutMapping( value = "/reservation/{reservationId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Reservation updateReservation(@PathVariable UUID reservationId, @RequestBody ReservationDTO reservationDTO)
    {
        log.info("updating reservation");
        Reservation reservationMapped = reservationMapper.reservationDTOToReservationEntity(reservationDTO);
        reservationMapped.setReservationId(reservationId);
        Reservation reservation = reservationService.updateReservation(reservationMapped);
        log.info("updated reservation");
        return reservation;
    }
    @DeleteMapping(path = "/reservation/{reservationId}")
    public void deleteByReservationId(@PathVariable UUID reservationId){
        log.info("deleting reservation");
        reservationService.deleteReservation(reservationId);
        log.info("deleted reservation");
    }

}
