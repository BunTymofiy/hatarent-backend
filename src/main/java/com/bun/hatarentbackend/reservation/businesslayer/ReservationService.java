package com.bun.hatarentbackend.reservation.businesslayer;

import com.bun.hatarentbackend.reservation.datalayer.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ReservationService {

    List<Reservation> findAllReservations();
    List<Reservation> findAllReservationsByUserId(UUID userId);
    List<Reservation> findAllReservationsByHostId(UUID hostId);
    Reservation acceptReservation(UUID reservationId);
    Reservation declineReservation(UUID reservationId);
    Reservation payReservation(UUID reservationId);
    Optional<Reservation> findReservationById(UUID reservationId);
    Reservation createReservation(Reservation reservationEntity);
    Reservation updateReservation(Reservation reservationEntity);
    void deleteReservation(UUID reservationId);
}
