package com.bun.hatarentbackend.property;

import com.bun.hatarentbackend.address.datalayer.AddressEntity;
import com.bun.hatarentbackend.property.datalayer.PropertyEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class PropertyEntityTest
{
    UUID uuid = UUID.randomUUID();
    UUID uuidHostId = UUID.randomUUID();
    @Test
    @DisplayName("Property AllArgsConstructor Test")
    void allArgsConstructorTest()
    {
        AddressEntity address = new AddressEntity();
        PropertyEntity property = new PropertyEntity(uuid, uuidHostId,address, 10, "SomeDescription", "Title","Name","Email", List.of("","") );

        assertEquals(uuid,property.getUuid());
        assertEquals(uuidHostId,property.getHostUserUuid());
        assertEquals(10,property.getGuestLimit());
        assertEquals("SomeDescription",property.getDescription());
        assertEquals("Title",property.getTitle());
        assertEquals("Name",property.getContact_person());
        assertEquals("Email",property.getEmail());
    }

    @Test
    @DisplayName("Property No ArgsConstructor Test")
    void noArgsConstructorTest()
    {
        PropertyEntity property = new PropertyEntity();
        assertNull(property.getHostUserUuid());
        assertNull(property.getGuestLimit());
        assertNull(property.getDescription());
    }

    @Test
    @DisplayName("Property Setter Test")
    void setterTest()
    {

        PropertyEntity property = new PropertyEntity();

        property.setUuid(uuid);
        property.setHostUserUuid(uuidHostId);
        property.setGuestLimit(10);
        property.setDescription("SomeDescription");
        property.setTitle("Title");
        property.setContact_person("Name");
        property.setEmail("Email");

        assertEquals(uuid,property.getUuid());
        assertEquals(uuidHostId,property.getHostUserUuid());
        assertEquals(10,property.getGuestLimit());
        assertEquals("SomeDescription",property.getDescription());
        assertEquals("Title",property.getTitle());
        assertEquals("Name",property.getContact_person());
        assertEquals("Email",property.getEmail());
    }
}
