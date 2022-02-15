package com.bun.hatarentbackend.reservation.datalayer;

import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.userservice.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDTO {

    private Property property;
    private UUID user;
    private Date checkInDate;
    private Date checkOutDate;
    private Integer numberGuests;
    private Float totalPrice;
    private String status;
}
