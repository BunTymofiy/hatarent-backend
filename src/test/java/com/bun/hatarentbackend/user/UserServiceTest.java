package com.bun.hatarentbackend.user;

import com.bun.hatarentbackend.property.businesslayer.PropertyServiceImpl;
import com.bun.hatarentbackend.userservice.domain.User;
import com.bun.hatarentbackend.userservice.repo.RoleRepo;
import com.bun.hatarentbackend.userservice.repo.UserRepo;
import com.bun.hatarentbackend.userservice.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

//@SpringBootTest(classes = {UserServiceImpl.class})
@ActiveProfiles({"test"})
public class UserServiceTest {

//    @MockBean
//    UserRepo userRepo;
//
//    @MockBean
//    private RoleRepo roleRepo;

    @Autowired
    @InjectMocks
    private UserServiceImpl userService;
    private User user1;
    private User user2;
    private User user3;
    private UUID uuid1;
    private UUID uuid2;
    private UUID uuid3;

    @Test
    void contextLoads(){}

    @Test
    void saveUser(){}

    @Test
    void updateUser(){}

    @Test
    void getUserById(){}

    @Test
    void getAllUsers(){}

    @Test
    void deleteUserById(){}

    @Test
    void deleteAllUsers(){}

    @Test
    void getUserEmail(){}

    @Test
    void filter(){}

    @BeforeEach
    void setUp() {
        uuid1 = UUID.fromString("2ff4fe29-d8ab-476a-9783-a3ac48bdcfd8");
        uuid2 = UUID.fromString("f7687281-6bbf-4498-b40d-d1ecbeab6bcb");
        uuid3 = UUID.fromString("4ad00d0b-c593-441b-a808-5b17e148142d");

    }
}
