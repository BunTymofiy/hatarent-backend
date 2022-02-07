package com.bun.hatarentbackend.night;

import com.bun.hatarentbackend.night.datalayer.Night;
import com.bun.hatarentbackend.night.datalayer.NightRepository;
import com.bun.hatarentbackend.property.datalayer.Address;
import com.bun.hatarentbackend.property.datalayer.Property;
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
public class NightRepoTest {
    UUID nightId = UUID.randomUUID();
    Property property = new Property();
    UUID uuidHostId = UUID.randomUUID();
    UUID uuidAddressId = UUID.randomUUID();

    @Autowired
    private NightRepository nightRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setUpDB()
    {
        nightRepository.deleteAll();
    }

    @Test
    public void saveNightTest()
    {
        Address address = new Address(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");
        UUID uuid = UUID.randomUUID();
        Property property1 = new Property(uuid, uuidHostId, address, 10, "SomeDescription", "Title","Name","Email",List.of("",""), 98.0, List.of(3.4,4.1));
        Date date  = new Date(2021, 11, 16);
        Night night1 = new Night(nightId, property1, date, 45.0f,"blocked");

        nightRepository.save(night1);
        assertThat(nightRepository.count()).isGreaterThan(0);
    }

    @Test
    public void deleteNightByIdTest()
    {

        Address address = new Address(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");
        UUID uuid = UUID.randomUUID();
        Property property1 = new Property(uuid, uuidHostId, address, 10, "SomeDescription", "Title","Name","Email",List.of("",""), 98.0, List.of(3.4,4.1));
        Date date  = new Date(2021, 11, 16);
        Night night1 = new Night(nightId, property1, date, 45.0f,"blocked");


        nightRepository.delete(night1);
    }

}
