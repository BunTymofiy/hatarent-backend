package com.bun.hatarentbackend.property;

import com.bun.hatarentbackend.property.datalayer.Address;
import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.property.datalayer.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
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
//    @Test
//    public void getPropertyByPropertyIdTest()
//    {
//        Address address = new Address(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");
//        entityManager.flush();
//        Property property1 = new Property(null, uuidHostId, address, 10, "SomeDescription", "Title","Name","Email",List.of("",""));
//        entityManager.flush();
//
//        Property save = propertyRepository.save(property1);
//        entityManager.flush();
//
//        Property property = propertyRepository.findById(save.getUuid()).get();
//        assertEquals(property.getUuid(), save.getUuid());
//    }
    @Test
    public void deletePropertyByIdTest()
    {
        Address address = new Address(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");

        UUID uuid = UUID.randomUUID();

        Property property1 = new Property(uuid, uuidHostId, address, 10, "SomeDescription", "Title","Name","Email",List.of("",""));
        propertyRepository.delete(property1);
    }
}
