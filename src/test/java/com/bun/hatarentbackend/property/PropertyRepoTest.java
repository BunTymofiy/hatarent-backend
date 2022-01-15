package com.bun.hatarentbackend.property;

import com.bun.hatarentbackend.property.datalayer.Address;
import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.property.datalayer.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class PropertyRepoTest
{

    UUID uuidHostId = UUID.randomUUID();
    UUID uuidAddressId = UUID.randomUUID();

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setUpDB()
    {
        propertyRepository.deleteAll();
    }

    @Test
    public void savePropertyTest()
    {
        Address address = new Address(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");
        UUID uuid = UUID.randomUUID();
        Property property1 = new Property(uuid, uuidHostId, address, 10, "SomeDescription", "Title","Name","Email",List.of("",""));

        propertyRepository.save(property1);
        assertThat(propertyRepository.count()).isGreaterThan(0);
    }
    @Test
    public void getPropertyByPropertyIdTest()
    {
        Address address = new Address(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");

        Property property1 = new Property(null, uuidHostId, address, 10, "SomeDescription", "Title","Name","Email",List.of("",""));
        Property save = propertyRepository.save(property1);
        entityManager.flush();
        Property property = propertyRepository.findById(save.getUuid()).get();
        assertEquals(property.getUuid(), save.getUuid());
    }
    @Test
    public void deletePropertyByIdTest()
    {
        Address address = new Address(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");

        UUID uuid = UUID.randomUUID();

        Property property1 = new Property(uuid, uuidHostId, address, 10, "SomeDescription", "Title","Name","Email",List.of("",""));
        propertyRepository.delete(property1);
    }


}
