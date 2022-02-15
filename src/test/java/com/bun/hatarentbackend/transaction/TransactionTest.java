package com.bun.hatarentbackend.transaction;

import com.bun.hatarentbackend.invoice.datalayer.Invoice;
import com.bun.hatarentbackend.transaction.datalayer.Transaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class TransactionTest {
    UUID transactionId = UUID.randomUUID();
    UUID senderId = UUID.randomUUID();
    UUID receiverId = UUID.randomUUID();
    Date date = new Date(2021, 11, 17);

    @Test
    @DisplayName("Transaction AllArgsConstructor Test")
    void allArgsConstructorTest(){
        Invoice invoice = new Invoice();
        Transaction transaction = new Transaction(transactionId, senderId, receiverId, date, invoice);

        assertEquals(transactionId, transaction.getTransactionId());
        assertEquals(senderId, transaction.getSenderId());
        assertEquals(receiverId, transaction.getReceiverId());
        assertEquals(date, transaction.getTransactionDate());
        assertEquals(invoice, transaction.getInvoice());
    }

    @Test
    @DisplayName("Transaction NoArgsConstructor Test")
    void noArgsConstructorTest(){
        Transaction transaction = new Transaction();

        assertNull(transaction.getSenderId());
        assertNull(transaction.getReceiverId());
        assertNull(transaction.getTransactionDate());
        assertNull(transaction.getInvoice());
    }

    @Test
    @DisplayName("Transaction Setter Test")
    void setterTest(){
        Invoice invoice = new Invoice();
        Transaction transaction = new Transaction();

        transaction.setTransactionId(transactionId);
        transaction.setTransactionDate(date);
        transaction.setSenderId(senderId);
        transaction.setReceiverId(receiverId);
        transaction.setInvoice(invoice);

        assertEquals(transactionId, transaction.getTransactionId());
        assertEquals(senderId, transaction.getSenderId());
        assertEquals(receiverId, transaction.getReceiverId());
        assertEquals(date, transaction.getTransactionDate());
        assertEquals(invoice, transaction.getInvoice());
    }

}
