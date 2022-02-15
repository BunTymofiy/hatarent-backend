package com.bun.hatarentbackend.transaction;

import com.bun.hatarentbackend.invoice.datalayer.Invoice;
import com.bun.hatarentbackend.property.datalayer.Address;
import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import com.bun.hatarentbackend.transaction.datalayer.Transaction;
import com.bun.hatarentbackend.transaction.datalayer.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class TransactionRepoTest {
    UUID transactionId = UUID.randomUUID();
    UUID senderId = UUID.randomUUID();
    UUID receiverId = UUID.randomUUID();
    UUID uuidHostId = UUID.randomUUID();
    UUID uuidAddressId = UUID.randomUUID();
    UUID reservationId = UUID.randomUUID();
    Date date = new Date(2020, 11, 5);
    UUID invoiceId = UUID.randomUUID();

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setUpDB(){
        transactionRepository.deleteAll();
    }

    @Test
    public void saveTransactionTest(){
        Address address = new Address(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");
        UUID uuid = UUID.randomUUID();
        Property property1 = new Property(uuid, uuidHostId, address, 10, "SomeDescription", "Title","Name","Email",List.of("",""), 98.0, List.of(3.4,4.1));
        Date date1  = new Date(2021, 11, 17);
        Date date2  = new Date(2021, 11, 19);
        Reservation reservation1 = new Reservation(reservationId, property1, uuidHostId, date1, date2, 3, 150.0);
        Invoice invoice = new Invoice(invoiceId, 56.0, reservation1);
        Transaction transaction = new Transaction(transactionId, senderId, receiverId, date, invoice);

        transactionRepository.save(transaction);
        assertThat(transactionRepository.count()).isGreaterThan(0);
    }

    @Test
    public void deleteTransactionByIdTest(){
        Address address = new Address(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");
        UUID uuid = UUID.randomUUID();
        Property property1 = new Property(uuid, uuidHostId, address, 10, "SomeDescription", "Title","Name","Email",List.of("",""), 98.0, List.of(3.4,4.1));
        Date date1  = new Date(2021, 11, 17);
        Date date2  = new Date(2021, 11, 19);
        Reservation reservation1 = new Reservation(reservationId, property1, uuidHostId, date1, date2, 3, 150.0);
        Invoice invoice = new Invoice(invoiceId, 56.0, reservation1);
        Transaction transaction = new Transaction(transactionId, senderId, receiverId, date, invoice);
        transactionRepository.delete(transaction);
        assertThat(transactionRepository.count()).isEqualTo(0);
    }
}
