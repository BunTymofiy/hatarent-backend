package com.bun.hatarentbackend.invoice.datalayer;

import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Entity
@Table(name="invoice")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Invoice {
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @Id
    private UUID invoiceId = UUID.randomUUID();

    @Column(name = "price")
    private Double price;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @NotNull(message = "Reservation is required")
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;
}
