package com.bun.hatarentbackend.userservice.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class UserPasswordLessDTO {
    private UUID uuid;
    private String email;
    private Collection<Role> roles = new ArrayList<>();
    private String firstName;
    private String lastName;
    private String username;
    private String image;
    private String phoneNumber;
}
