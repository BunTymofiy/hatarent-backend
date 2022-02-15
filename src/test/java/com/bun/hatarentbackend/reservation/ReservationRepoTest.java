package com.bun.hatarentbackend.reservation;

import com.bun.hatarentbackend.property.datalayer.Address;
import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.reservation.datalayer.Reservation;
import com.bun.hatarentbackend.reservation.datalayer.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class ReservationRepoTest {
    UUID reservationId = UUID.randomUUID();
    Property property = new Property();
    UUID uuidHostId = UUID.randomUUID();
    UUID uuidAddressId = UUID.randomUUID();

    @Mock
    private ReservationRepository reservationRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setUpDB()
    {
        reservationRepository.deleteAll();
    }

    @Test
    public void saveReservationTest() {
        Address address = new Address(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");
        UUID uuid = UUID.randomUUID();
        Property property1 = new Property(uuid, uuidHostId, address, 10, "SomeDescription", "Title","Name","Email",List.of("",""), 98.0, List.of(3.4,4.1));
        Date date1  = new Date(2021, 11, 17);
        Date date2  = new Date(2021, 11, 19);
        Reservation reservation1 = new Reservation(reservationId, property1, uuidHostId, date1, date2, 3, 150.0,"");

        reservationRepository.save(reservation1);
        assertThat(reservationRepository.count()).isEqualTo(0);
    }

    @Test
    public void deleteReservationByIdTest()
    {
        Address address = new Address(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");
        UUID uuid = UUID.randomUUID();
        Property property1 = new Property(uuid, uuidHostId, address, 10, "SomeDescription", "Title","Name","Email",List.of("",""), 98.0, List.of(3.4,4.1));
        Date date1  = new Date(2021, 11, 17);
        Date date2  = new Date(2021, 11, 19);
        Reservation reservation1 = new Reservation(reservationId, property1, uuidHostId, date1, date2, 3, 150.0,"");

        reservationRepository.delete(reservation1);
    }
}
