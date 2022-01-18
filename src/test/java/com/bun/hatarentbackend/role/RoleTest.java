package com.bun.hatarentbackend.role;

import com.bun.hatarentbackend.userservice.domain.Role;
import com.bun.hatarentbackend.userservice.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class RoleTest
{
    final UUID MY_UUID = UUID.randomUUID();
    @Test
    @DisplayName("User AllArgsConstructor Test")
    void allArgsConstructorTest()
    {
        Role role = new Role(MY_UUID,"ROLE_USER");
        assertEquals(MY_UUID,role.getUuid());
        assertEquals("ROLE_USER",role.getName());
    }

    @Test
    @DisplayName("Property No ArgsConstructor Test")
    void noArgsConstructorTest()
    {
        Role role = new Role();
        assertNull(role.getName());
    }

    @Test
    @DisplayName("Property Setter Test")
    void setterTest()
    {
        Role role = new Role();
        role.setUuid(MY_UUID);
        role.setName("ROLE_USER");
        assertEquals(MY_UUID,role.getUuid());
        assertEquals("ROLE_USER",role.getName());

    }
}
