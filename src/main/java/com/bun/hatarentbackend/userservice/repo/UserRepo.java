package com.bun.hatarentbackend.userservice.repo;

import com.bun.hatarentbackend.userservice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    User findByEmail(String email);
}
