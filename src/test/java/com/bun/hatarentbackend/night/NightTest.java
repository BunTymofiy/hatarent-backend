package com.bun.hatarentbackend.night;

import com.bun.hatarentbackend.night.datalayer.Night;
import com.bun.hatarentbackend.property.datalayer.Property;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.UUID;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class NightTest {
    UUID nightId = UUID.randomUUID();

    @Test
    @DisplayName("Night AllArgsConstructor Test")
    void allArgsConstructorTest(){
        Property property = new Property();
        Date date = new Date(2021, 11, 17);
        Night night = new Night(nightId, property, date, 44.0f, "available");

        assertEquals(nightId, night.getNightId());
        assertEquals(property, night.getPropertyId());
        assertEquals(date, night.getDate());
        assertEquals(44.0f, night.getPrice());
        assertEquals("available", night.getState());
    }

    @Test
    @DisplayName("Night No ArgsConstructor Test")
    void noArgsConstructorTest()
    {
        Night night = new Night();
        assertNull(night.getPropertyId());
        assertNull(night.getDate());
        assertNull(night.getPrice());
        assertNull(night.getState());
    }

    @Test
    @DisplayName("Night Setter Test")
    void setterTest()
    {
        Property property = new Property();
        Night night = new Night();
        Date date = new Date(2021, 11, 17);

        night.setNightId(nightId);
        night.setPropertyId(property);
        night.setDate(date);
        night.setPrice(44.0f);
        night.setState("blocked");

        assertEquals(nightId,night.getNightId());
        assertEquals(property,night.getPropertyId());
        assertEquals(date,night.getDate());
        assertEquals(44.0f,night.getPrice());
        assertEquals("blocked",night.getState());
    }

}
