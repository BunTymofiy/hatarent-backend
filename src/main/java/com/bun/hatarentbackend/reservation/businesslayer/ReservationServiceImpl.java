package com.bun.hatarentbackend.reservation.businesslayer;

import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import com.bun.hatarentbackend.reservation.datalayer.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Optional<Reservation> findReservationById(UUID reservationId) {
        log.info("Retrieving Reservation");
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        return reservation;
    }

    @Override
    public Reservation createReservation(Reservation reservationEntity) {
        log.info("Creating Reservation");
        Reservation reservation = reservationRepository.save(reservationEntity);
        log.info("Created Reservation");
        return null;
    }

    @Override
    public Reservation updateReservation(Reservation reservationEntity) {
        log.info("Creating Reservation");
        Reservation reservation = reservationRepository.save(reservationEntity);
        log.info("Created Reservation");
        return null;
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
