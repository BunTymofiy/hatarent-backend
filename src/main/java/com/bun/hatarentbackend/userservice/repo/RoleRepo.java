package com.bun.hatarentbackend.userservice.repo;

import com.bun.hatarentbackend.userservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {
    Role findByName(String name);
}
