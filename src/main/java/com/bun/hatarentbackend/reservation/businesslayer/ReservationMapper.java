package com.bun.hatarentbackend.reservation.businesslayer;

import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import com.bun.hatarentbackend.reservation.datalayer.ReservationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    @Mapping(target = "reservationId", ignore = true)
    Reservation reservationDTOToReservationEntity(ReservationDTO reservationDTO);
    ReservationDTO reservationEntityToReservationDTO(Reservation reservation);
}
