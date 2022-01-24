package com.bun.hatarentbackend.userservice.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "users")
public class User {

    @GeneratedValue(generator = "UUIDGenerator")
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @Id
    private UUID uuid = UUID.randomUUID();
    private String firstName;
    private String lastName;
    private String username;
    @Column(unique = true)
    @NotNull(message = "Email cannot be null")
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER,cascade = { CascadeType.MERGE})
    private Collection<Role> roles = new ArrayList<>();
    private String image;
    private String phoneNumber;
}

