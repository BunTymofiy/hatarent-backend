package com.bun.hatarentbackend.user;

import com.bun.hatarentbackend.presentationlayer.controller.UserController;
import com.bun.hatarentbackend.userservice.domain.Role;
import com.bun.hatarentbackend.userservice.domain.User;
import com.bun.hatarentbackend.userservice.domain.UserMapper;
import com.bun.hatarentbackend.userservice.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.reset;

@SpringBootTest(classes = {UserController.class})
@ActiveProfiles({"test"})
public class UserControllerTest {
    private static final String VALID_EMAIL = "random@email.com";
    private static final String INVALID_EMAIL = "invalidEmail";
    private static final String VALID_PASSWORD = "S0m3P@ssw0rd";
    private static final String INVALID_PASSWORD = "invalidpassword";
    private static final String VALID_ROLE = "ROLE_USER";
//    private static  Collection<Role> = new ArrayList<>();
    private static final User VALID_USER_DTO = User.builder()
            .email(VALID_EMAIL)
            .password(VALID_PASSWORD)
//            .roles(new VALID_ROLE)
            .build();
    @Autowired
    private UserController userController;

    @Autowired
    private UserMapper userMapper;

    @MockBean
    private UserService userService;

//    @Test
//    void contextLoads(){
//        reset(userService);
//    }
//    @Test
//    @DisplayName("CNKLYFCeTg: Given user with null email, then throw ConstrainViolationException")
//    void registerUser_nullEmail() {
//
//        // Arrange
//        User userUUIDLessDTO = VALID_USER_DTO.toBuilder()
//                .email(null)
//                .password(VALID_PASSWORD)
//                .build();
//
//        // Act
//        ConstraintViolationException exception =
//                assertThrows(ConstraintViolationException.class, () -> userController.registerGuest(User);
//
//        // Assert
//        final Set<String> collect = ConstraintViolationExceptionUtils.extractMessages(exception);
//        assertThat(collect).containsExactly("Email must not be blank");
//
//        verify(userService, times(0)).create(userUUIDLessDTO);
//    }
}
