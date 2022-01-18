package com.bun.hatarentbackend.role;


import com.bun.hatarentbackend.userservice.domain.Role;
import com.bun.hatarentbackend.userservice.repo.RoleRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class RoleRepoTest
{
    @Autowired
    private RoleRepo roleRepo;

    @Test
    void contextLoads(){}

    @Test
    @DisplayName("Get all roles")
    void retrieveAllRoles() {
        final List<Role> all = roleRepo.findAll();
        assertEquals(2, all.size());
        assertThat(all.stream().map(Role::getName))
                .containsExactlyInAnyOrder("ROLE_GUEST", "ROLE_HOST");
    }
    @Test
    @DisplayName("Given existing role, retrieve by name")
    void retrieveByName(){
        // Arrange
        final String name = "ROLE_HOST";

        // Act
        final Optional<Role> role = Optional.ofNullable(roleRepo.findByName(name));

        // Assert
        assertTrue(role.isPresent());

        final Role retrieved = role.get();
        assertEquals(name, retrieved.getName());
    }
}
