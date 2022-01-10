package com.bun.hatarentbackend.property;

import com.bun.hatarentbackend.address.datalayer.AddressEntity;
import com.bun.hatarentbackend.property.datalayer.PropertyEntity;
import com.bun.hatarentbackend.property.datalayer.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

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
        AddressEntity addressEntity = new AddressEntity(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");
        UUID uuid = UUID.randomUUID();
        PropertyEntity property1 = new PropertyEntity(uuid, uuidHostId, addressEntity, 10, "SomeDescription", "Title","Name","Email",List.of("",""));

        propertyRepository.save(property1);
        assertThat(propertyRepository.count()).isGreaterThan(0);
    }
    @Test
    public void getPropertyByPropertyIdTest()
    {
        AddressEntity addressEntity = new AddressEntity(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");

        PropertyEntity property1 = new PropertyEntity(null, uuidHostId, addressEntity, 10, "SomeDescription", "Title","Name","Email",List.of("",""));
        PropertyEntity save = propertyRepository.save(property1);
        entityManager.flush();
        PropertyEntity property = propertyRepository.findById(save.getUuid()).get();
        assertEquals(property.getUuid(), save.getUuid());
    }
    @Test
    public void deletePropertyByIdTest()
    {
        AddressEntity addressEntity = new AddressEntity(uuidAddressId, "Street", "apartment","City", "State", "Zip", "Country", "Latitude", "Longitude");

        UUID uuid = UUID.randomUUID();

        PropertyEntity property1 = new PropertyEntity(uuid, uuidHostId, addressEntity, 10, "SomeDescription", "Title","Name","Email",List.of("",""));
        propertyRepository.delete(property1);
    }


}
