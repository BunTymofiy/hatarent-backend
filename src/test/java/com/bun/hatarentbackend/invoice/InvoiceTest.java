package com.bun.hatarentbackend.invoice;

import com.bun.hatarentbackend.invoice.datalayer.Invoice;
import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class InvoiceTest {
    UUID invoiceId = UUID.randomUUID();

    @Test
    @DisplayName("Invoice AllArgsConstructor Test")
    void allArgsConstructorTest(){
        Reservation reservation = new Reservation();
        Invoice invoice = new Invoice(invoiceId, 35.0, reservation);

        assertEquals(invoiceId, invoice.getInvoiceId());
        assertEquals(35.0, invoice.getPrice());
        assertEquals(reservation, invoice.getReservation());
    }

    @Test
    @DisplayName("Invoice NoArgsconstructor Test")
    void noArgsConstructorTest(){
        Invoice invoice = new Invoice();
        assertNull(invoice.getPrice());
        assertNull(invoice.getReservation());
    }

    @Test
    @DisplayName("Invoice Setter Test")
    void setterTest(){
        Reservation reservation = new Reservation();
        Invoice invoice = new Invoice();

        invoice.setInvoiceId(invoiceId);
        invoice.setPrice(35.0);
        invoice.setReservation(reservation);

        assertEquals(invoiceId,invoice.getInvoiceId());
        assertEquals(reservation, invoice.getReservation());
        assertEquals(35.0, invoice.getPrice());
    }
}
