package com.bun.hatarentbackend.night.businesslayer;

import com.bun.hatarentbackend.night.datalayer.Night;
import com.bun.hatarentbackend.night.datalayer.NightRepository;
import com.bun.hatarentbackend.reservation.businesslayer.ReservationService;
import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j

public class NightServiceImpl implements NightService{
    private final ReservationService reservationService;
    private final NightRepository nightRepository;

    public NightServiceImpl(ReservationService reservationService, NightRepository nightRepository) {
        this.reservationService = reservationService;
        this.nightRepository = nightRepository;
    }

    @Override
    public List<Night> findAllNights() {
        log.info("Retrieving all nights");
        return nightRepository.findAll();
    }

    @Override
    public Optional<Night> findNightById(UUID nightId) {
        log.info("Retrieving Night");
        Optional<Night> night = nightRepository.findById(nightId);
        return night;
    }

    @Override
    public List<Night> findNightsByReservation(Reservation reservation) {
        List<Night> nights = nightRepository.findByPropertyUuid(reservation.getProperty().getUuid());
        List<Night> filteredNights = new ArrayList<>(nights);
        for (Night night : nights) {
            if (night.getDate().before(reservation.getCheckOutDate()) && night.getDate().after(reservation.getCheckInDate()) || night.getDate().equals(reservation.getCheckInDate()) || night.getDate().equals(reservation.getCheckOutDate())) {
                filteredNights.add(night);  //add night to list if it is in the reservation period
            }
        }
        return filteredNights;
    }

    @Override
    public List<Night> createNights(List<Night> nights) {
        log.info("Creating Nights");
        List<Night> createdNights = nightRepository.saveAll(nights);
        log.info("Created nights");
        return createdNights;
    }


    @Override
    public Night createNight(Night nightEntity) {
        log.info("Creating Night");
        Night night = nightRepository.save(nightEntity);
        log.info("Created Night");
        return night;
    }

    @Override
    public List<Night> updateNights(UUID reservationId) {
        log.info("Updating Nights");
        Reservation reservation = reservationService.findReservationById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));
        List<Night> nights = findNightsByReservation(reservation);
        List<Night> nightsFiltered = new ArrayList<>();
        for (Night night : nights) {
            night.setState("reserved");
            nightsFiltered.add(night);
        }
        nightRepository.saveAll(nightsFiltered);
        log.info("Updated Nights");
        return nightsFiltered;
    }

    @Override
    public Night updateNight(Night nightEntity) {
        log.info("Creating Night");
        Night night = nightRepository.save(nightEntity);
        log.info("Created Night");
        return night;
    }

    @Override
    public void deleteNights(UUID reservationId) {
        log.info("Deleting Nights");
        Reservation reservation = reservationService.findReservationById(reservationId).orElseThrow(() -> new RuntimeException("Reservation not found"));
        List<Night> nights = findNightsByReservation(reservation);
        nightRepository.deleteAll(nights);
        log.info("Deleted Nights");
    }

    @Override
    @Transactional
    public void deleteNight(UUID nightId) {
        log.info("Deleting Night");
        final Integer deleted = nightRepository.deleteByNightId(nightId);
        if(deleted<=0){
            log.info("Night not found");
            return;
        }
        log.info("Night was successfully deleted");
    }
}
