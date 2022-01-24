package com.bun.hatarentbackend.userservice.service;


import com.bun.hatarentbackend.userservice.domain.Role;
import com.bun.hatarentbackend.userservice.domain.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String roleName);
    User getUser(String username);
    List<User> getUsers();
    boolean checkIfUserExist(String email);
    User register (User user) throws Exception;
}
