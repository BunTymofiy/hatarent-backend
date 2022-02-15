package com.bun.hatarentbackend.transaction.datalayer;

import com.bun.hatarentbackend.invoice.datalayer.Invoice;
import com.bun.hatarentbackend.property.datalayer.Address;
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
@Table(name="transaction")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaction {
    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @Id
    private UUID transactionId = UUID.randomUUID();

    @Column(name = "sender_user_uuid")
    private UUID senderId;

    @Column(name = "receiver_user_uuid")
    private UUID receiverId;

    @Column(name = "transaction_date")
    private Date transactionDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @NotNull(message = "Invoice is required")
    @JoinColumn(name = "invoice")
    private Invoice invoice;
}
