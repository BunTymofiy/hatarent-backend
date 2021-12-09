package com.bun.hatarentbackend.property;

import com.bun.hatarentbackend.property.datalayer.PropertyEntity;
import com.bun.hatarentbackend.property.datalayer.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.PropertyAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

@DataJpaTest
public class PropertyRepoTest
{


    @Autowired
    private PropertyRepository propertyRepository;
    @BeforeEach
    public void setUpDB()
    {

        PropertyEntity property1 = new PropertyEntity(1,1,1,1,3,"SomeDescription", "Title");
        PropertyEntity property2 = new PropertyEntity(2,2,2,2,2,"SomeDescription", "Title");
        PropertyEntity property3 = new PropertyEntity(3,3,3,3,6,"SomeDescription", "Title");
        propertyRepository.save(property1);
        propertyRepository.save(property2);
        propertyRepository.save(property3);
    }

    @Test
    public void savePropertyTest()
    {
        PropertyEntity property1 = new PropertyEntity(1,5,2,3,3,"SomeDescription", "Title");
        propertyRepository.save(property1);
        assertThat(propertyRepository.count()).isGreaterThan(0);
    }
    @Test
    public void getPropertyByPropertyIdTest()
    {
        PropertyEntity property = propertyRepository.findByPropertyId(1).get();
        assertEquals(property.getPropertyId(), 1);
    }
    @Test
    public void deletePropertyByIdTest()
    {
        PropertyEntity property1 = new PropertyEntity(1,1,1,1,3,"SomeDescription", "Title");
        propertyRepository.delete(property1);
    }


}
