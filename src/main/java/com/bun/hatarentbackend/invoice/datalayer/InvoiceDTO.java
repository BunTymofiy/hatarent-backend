package com.bun.hatarentbackend.invoice.datalayer;

import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {
    private Double price;
    private Reservation reservation;
}
