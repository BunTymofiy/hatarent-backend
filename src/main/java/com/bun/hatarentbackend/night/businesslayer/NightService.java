package com.bun.hatarentbackend.night.businesslayer;

import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.night.datalayer.Night;
import com.bun.hatarentbackend.reservation.datalayer.Reservation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NightService {

    List<Night> findAllNights();
    Optional<Night> findNightById(UUID nightId);
    List<Night> findNightsByReservation(Reservation reservation);
    List<Night> createNights(List<Night> nights);
    Night createNight(Night nightEntity);
    List<Night> updateNights(UUID reservationId);
    Night updateNight(Night nightEntity);
    void deleteNights(UUID reservationId);
    void deleteNight(UUID nightId);
}
