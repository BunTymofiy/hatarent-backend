package com.bun.hatarentbackend.reservation.datalayer;

import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.userservice.domain.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="reservation")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {

    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @Id
    private UUID reservationId = UUID.randomUUID();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @NotNull(message = "Property is required")
    @JoinColumn(name = "uuid")
    private Property property;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @NotNull(message = "Property is required")
    @Column(name = "user_uuid")
    private UUID user;

    @Column(name = "check_in_date")
    private Date checkInDate;

    @Column(name = "check_out_date")
    private Date checkOutDate;

    @Column(name = "number_guests")
    private Integer numberGuests;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "status")
    private String status;
}
