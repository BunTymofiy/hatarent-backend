package com.bun.hatarentbackend.reservation;

import com.bun.hatarentbackend.property.datalayer.Address;
import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class ReservationTest {
    UUID reservationId = UUID.randomUUID();
    UUID uuidGuestId = UUID.randomUUID();

    @Test
    @DisplayName("Reservation AllArgsConstructor Test")
    void allArgsConstructorTest()
    {

        Property property = new Property();
        Date date1 = new Date(2021, 11, 17);
        Date date2 = new Date(2021, 11, 20);
        Reservation reservation = new Reservation(reservationId, property, uuidGuestId, date1, date2, 2, 65.0f);


        assertEquals(reservationId,reservation.getReservationId());
        assertEquals(property,reservation.getProperty());
        assertEquals(uuidGuestId,reservation.getUser());
        assertEquals(date1,reservation.getCheckInDate());
        assertEquals(date2,reservation.getCheckOutDate());
        assertEquals(2,reservation.getNumberGuests());
        assertEquals(65.0f,reservation.getTotalPrice());
    }

    @Test
    @DisplayName("Reservation No ArgsConstructor Test")
    void noArgsConstructorTest()
    {
        Reservation reservation = new Reservation();
        assertNull(reservation.getProperty());
        assertNull(reservation.getUser());
        assertNull(reservation.getCheckInDate());
        assertNull(reservation.getCheckOutDate());
        assertNull(reservation.getNumberGuests());
        assertNull(reservation.getTotalPrice());


    }

    @Test
    @DisplayName("Reservation Setter Test")
    void setterTest()
    {

        Reservation reservation = new Reservation();
        Property property = new Property();
        Date date1 = new Date(2021, 11, 17);
        Date date2 = new Date(2021, 11, 20);

        reservation.setReservationId(reservationId);
        reservation.setProperty(property);
        reservation.setUser(uuidGuestId);
        reservation.setCheckInDate(date1);
        reservation.setCheckOutDate(date2);
        reservation.setNumberGuests(2);
        reservation.setTotalPrice(65.0f);


        assertEquals(reservationId,reservation.getReservationId());
        assertEquals(property,reservation.getProperty());
        assertEquals(uuidGuestId,reservation.getUser());
        assertEquals(date1,reservation.getCheckInDate());
        assertEquals(date2,reservation.getCheckOutDate());
        assertEquals(2,reservation.getNumberGuests());
        assertEquals(65.0f,reservation.getTotalPrice());
    }

}
