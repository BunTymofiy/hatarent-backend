package com.bun.hatarentbackend.property;

import com.bun.hatarentbackend.property.datalayer.PropertyEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PropertyEntityTest
{
    @Test
    @DisplayName("Property AllArgsConstructor Test")
    void allArgsConstructorTest()
    {

        PropertyEntity property = new PropertyEntity(1,1,1,1,3,"SomeDescription", "Title");

        assertEquals(1,property.getId());
        assertEquals(1,property.getPropertyId());
        assertEquals(1,property.getHostUserId());
        assertEquals(1,property.getAddressId());
        assertEquals(3,property.getGuestLimit());
        assertEquals("SomeDescription",property.getDescription());
        assertEquals("Title",property.getTitle());
    }

    @Test
    @DisplayName("Property No ArgsConstructor Test")
    void noArgsConstructorTest()
    {
        PropertyEntity property = new PropertyEntity();
        assertNull(property.getId());
        assertNull(property.getPropertyId());
        assertNull(property.getHostUserId());
        assertNull(property.getAddressId());
        assertNull(property.getGuestLimit());
        assertNull(property.getDescription());
    }

    @Test
    @DisplayName("Property Setter Test")
    void setterTest()
    {

        PropertyEntity property = new PropertyEntity();
        property.setId(1);
        property.setPropertyId(1);
        property.setAddressId(1);
        property.setHostUserId(1);
        property.setGuestLimit(3);
        property.setDescription("SomeDescription");

        assertEquals(1,property.getId());
        assertEquals(1,property.getPropertyId());
        assertEquals(1,property.getHostUserId());
        assertEquals(1,property.getAddressId());
        assertEquals(3,property.getGuestLimit());
        assertEquals("SomeDescription",property.getDescription());
    }
}
