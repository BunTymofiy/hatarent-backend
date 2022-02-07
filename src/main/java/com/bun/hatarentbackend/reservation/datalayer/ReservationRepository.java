package com.bun.hatarentbackend.reservation.datalayer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID>{
    Integer deleteByReservationId(UUID id);
}
