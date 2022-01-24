package com.bun.hatarentbackend.user;

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
public class UserTest {
    @Test
    @DisplayName("User AllArgsConstructor Test")
    void allArgsConstructorTest()
    {
        Collection<Role> roles = new ArrayList<>();
        UUID uudi = UUID.randomUUID();
        User user = new User(uudi,"FirstName","LastName","UserName","Email","Password", roles,"Image","phoneNumber");
        assertEquals("FirstName",user.getFirstName());
        assertEquals("LastName",user.getLastName());
        assertEquals("UserName",user.getUsername());
        assertEquals("Email",user.getEmail());
        assertEquals("Password",user.getPassword());
        assertEquals("Image",user.getImage());
    }

    @Test
    @DisplayName("Property No ArgsConstructor Test")
    void noArgsConstructorTest()
    {
        Collection<Role> roles = new ArrayList<>();
        User user = new User();
        assertNull(user.getFirstName());
        assertNull(user.getLastName());
        assertNull(user.getUsername());
        assertNull(user.getEmail());
        assertNull(user.getPassword());
        assertNull(user.getImage());
        assertNull(user.getPhoneNumber());
    }

    @Test
    @DisplayName("Property Setter Test")
    void setterTest()
    {
        Collection<Role> roles = new ArrayList<>();
        User user = new User();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setUsername("UserName");
        user.setEmail("Email");
        user.setPassword("Password");
        user.setImage("Image");
        user.setPhoneNumber("phoneNumber");
        assertEquals("FirstName",user.getFirstName());
        assertEquals("LastName",user.getLastName());
        assertEquals("UserName",user.getUsername());
        assertEquals("Email",user.getEmail());
        assertEquals("Password",user.getPassword());
        assertEquals("Image",user.getImage());
        assertEquals("phoneNumber",user.getPhoneNumber());
    }
}
