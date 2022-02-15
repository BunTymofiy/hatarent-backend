package com.bun.hatarentbackend.transaction.datalayer;

import com.bun.hatarentbackend.invoice.datalayer.Invoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private UUID senderId;
    private UUID receiverId;
    private Date transactionDate;
    private Invoice invoice;
}
