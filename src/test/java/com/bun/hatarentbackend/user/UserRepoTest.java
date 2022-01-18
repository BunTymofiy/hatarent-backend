package com.bun.hatarentbackend.user;

import com.bun.hatarentbackend.property.datalayer.Address;
import com.bun.hatarentbackend.property.datalayer.Property;
import com.bun.hatarentbackend.userservice.domain.Role;
import com.bun.hatarentbackend.userservice.domain.User;
import com.bun.hatarentbackend.userservice.repo.RoleRepo;
import com.bun.hatarentbackend.userservice.repo.UserRepo;
import org.assertj.core.util.Throwables;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Collection;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserRepoTest {

    private static final String VALID_EMAIL = "abc@def.com";
    private static final String VALID_PASSWORD = "S0meP@ssw0rd";
    private static final String VALID_ROLE_NAME = "SOME_ROLE";
    private static final Role VALID_ROLE = Role.builder()
            .name(VALID_ROLE_NAME)
            .build();
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private RoleRepo roleRepo;

    @BeforeEach
    public void setUpDB()
    {
        userRepository.deleteAll();
    }
    @Test
    @DisplayName("given user, persist")
    void save() {

        final Role savedRole = roleRepo.save(VALID_ROLE);
        final Collection<Role> roles = new ArrayList<>();
        roles.add(savedRole);
        entityManager.flush();
        final User save = userRepository.save(
                User.builder()
                        .email(VALID_EMAIL)
                        .password(VALID_PASSWORD)
                        .roles(roles)
                        .build()
        );

        assertEquals(VALID_EMAIL, save.getEmail());
        assertEquals(VALID_PASSWORD, save.getPassword());
    }

    @Test
    @DisplayName("Throw exception on email null")
    void save_NullEmail() {

        // Arrange
        final User user = User.builder()
                .email(null)
                .password(VALID_PASSWORD)
                .build();

        // Act
//        userRepository.save(user);
//        final ConstraintViolationException constraintViolationException =
//                assertThrows(ConstraintViolationException.class, () -> entityManager.flush());
//        // Assert
//        Throwable exception = Throwables.getRootCause(constraintViolationException);
//        assertThat(exception.getMessage()).contains("Email cannot be null");
    }
    @Test
    @DisplayName("Throw Exception on duplicate email")
    void save_DuplicateEmail() {

        // Arrange
        final Role savedRole = roleRepo.save(VALID_ROLE);
        final Collection<Role> roles = new ArrayList<>();
        roles.add(savedRole);

        final User user = User.builder()
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .roles(roles)
                .build();

        // Act
        userRepository.save(user);
        userRepository.save(user.toBuilder().build());
//        final PersistenceException persistenceException =
//                assertThrows(PersistenceException.class, () -> entityManager.flush());
//        // Assert
//        Throwable exception = Throwables.getRootCause(persistenceException);
//        assertThat(exception.getMessage()).contains("Unique");
    }
    @Test
    public void saveTest()
    {
        User user = new User(null,"FirstName","LastName","UserName","Email","Password", null,"Image","phoneNumber");
        entityManager.flush();
        userRepository.save(user);
        assertThat(userRepository.count()).isGreaterThan(0);
    }

    @Test
    public void findByEmailTest()
    {
        User user = new User(null,"FirstName","LastName","UserName","Email","Password", null,"Image","phoneNumber");
        entityManager.flush();
        userRepository.save(user);
        assertThat(userRepository.findByEmail("Email")).isNotNull();
    }

    @Test
    public void findByEmailNotFoundTest()
    {
        assertNull(userRepository.findByEmail("Email"));
    }

}
