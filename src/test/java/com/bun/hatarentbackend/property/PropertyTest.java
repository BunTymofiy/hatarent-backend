package com.bun.hatarentbackend.property;

import com.bun.hatarentbackend.property.datalayer.Address;
import com.bun.hatarentbackend.property.datalayer.Property;
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
public class PropertyTest
{
    UUID uuid = UUID.randomUUID();
    UUID uuidHostId = UUID.randomUUID();
    @Test
    @DisplayName("Property AllArgsConstructor Test")
    void allArgsConstructorTest()
    {
        Address address = new Address();
        Property property = new Property(uuid, uuidHostId,address, 10, "SomeDescription", "Title","Name","Email", List.of("","") , 98.0, List.of(3.4,4.1));

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
        Property property = new Property();
        assertNull(property.getHostUserUuid());
        assertNull(property.getGuestLimit());
        assertNull(property.getDescription());
    }

    @Test
    @DisplayName("Property Setter Test")
    void setterTest()
    {

        Property property = new Property();

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
