package com.bun.hatarentbackend.reservation.businesslayer;

import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import com.bun.hatarentbackend.reservation.datalayer.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public List<Reservation> findAllReservations() {
        log.info("Retrieving all reservations");
        return reservationRepository.findAll();
    }

    @Override
    public List<Reservation> findAllReservationsByUserId(UUID userId) {
        log.info("Retrieving all reservations");
        return reservationRepository.findByUser(userId);
    }

    @Override
    public List<Reservation> findAllReservationsByHostId(UUID hostId) {
        log.info("Retrieving all reservations");
        List<Reservation> reservations = reservationRepository.findByPropertyHostUserUuid(hostId);
        log.info("Retrieved all reservations");
        return reservations;
    }

    @Override
    public List<Reservation> findAllReservationByPropertyUuid(UUID propertyUuid) {
        log.info("Retrieving all reservations by property");
        List<Reservation> reservations = reservationRepository.findByPropertyUuid(propertyUuid);
        log.info("Retrieved all reservations by property");
        return reservations;
    }

    @Override
    public Reservation acceptReservation(UUID reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()-> new RuntimeException("Reservation not found"));
        reservation.setStatus("accepted");
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation declineReservation(UUID reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()-> new RuntimeException("Reservation not found"));
        reservation.setStatus("declined");
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation payReservation(UUID reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(()-> new RuntimeException("Reservation not found"));
        reservation.setStatus("paid");
        return reservationRepository.save(reservation);
    }

    @Override
    public Optional<Reservation> findReservationById(UUID reservationId) {
        log.info("Retrieving Reservation");
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        log.info("Retrieved Reservation");
        return reservation;
    }

    @Override
    public Reservation createReservation(Reservation reservationEntity) {
        log.info("Creating Reservation");
        Reservation reservation = reservationRepository.save(reservationEntity);
        log.info("Created Reservation");
        return reservation;
    }

    @Override
    public Reservation updateReservation(Reservation reservationEntity) {
        log.info("Creating Reservation");
        Reservation reservation = reservationRepository.save(reservationEntity);
        log.info("Created Reservation");
        return reservation;
    }

    @Override
    @Transactional
    public void deleteReservation(UUID reservationId) {
        log.info("Deleting Reservation");
        final Integer deleted = reservationRepository.deleteByReservationId(reservationId);
        if(deleted<=0){
            log.info("Reservation not found");
            return;
        }
        log.info("Reservation was successfully deleted");
    }
}
