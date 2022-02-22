package com.bun.hatarentbackend.userservice.service;


import com.bun.hatarentbackend.userservice.domain.Role;
import com.bun.hatarentbackend.userservice.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String email);
    User getUserByUuid(UUID uuid);
    List<User> getUsers();
    boolean checkIfUserExist(String email);
    User register (User user) throws Exception;
}
