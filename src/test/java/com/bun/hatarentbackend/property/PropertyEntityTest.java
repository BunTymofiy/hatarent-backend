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
        UUID propertyUuid = UUID.randomUUID();
        UUID host_userUuid = UUID.randomUUID();
        UUID address_idUuid = UUID.randomUUID();
        PropertyEntity property = new PropertyEntity(1,propertyUuid,host_userUuid,address_idUuid,3,"SomeDescription");

        assertEquals(1,property.getId());
        assertEquals(propertyUuid,property.getPropertyId());
        assertEquals(host_userUuid,property.getHostUserId());
        assertEquals(address_idUuid,property.getAddressId());
        assertEquals(3,property.getGuestLimit());
        assertEquals("SomeDescription",property.getDescription());
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
        UUID propertyUuid = UUID.randomUUID();
        UUID host_userUuid = UUID.randomUUID();
        UUID address_idUuid = UUID.randomUUID();
        PropertyEntity property = new PropertyEntity();
        property.setId(1);
        property.setPropertyId(propertyUuid);
        property.setAddressId(address_idUuid);
        property.setHostUserId(host_userUuid);
        property.setGuestLimit(3);
        property.setDescription("SomeDescription");

        assertEquals(1,property.getId());
        assertEquals(propertyUuid,property.getPropertyId());
        assertEquals(host_userUuid,property.getHostUserId());
        assertEquals(address_idUuid,property.getAddressId());
        assertEquals(3,property.getGuestLimit());
        assertEquals("SomeDescription",property.getDescription());
    }
}
