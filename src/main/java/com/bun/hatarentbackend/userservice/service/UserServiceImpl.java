package com.bun.hatarentbackend.userservice.service;

import com.bun.hatarentbackend.userservice.domain.Role;
import com.bun.hatarentbackend.userservice.domain.User;
import com.bun.hatarentbackend.userservice.repo.RoleRepo;
import com.bun.hatarentbackend.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService
{
    private final UserRepo userRepository;
    private final RoleRepo roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("loading user by email {}", email);
        User user = userRepository.findByEmail(email);
        if (user == null) {
            log.error("User not found in the db");
            throw new UsernameNotFoundException("User not found in the db");
        }
        else {
            log.info("User found in the db: {}", user);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }
    @Override
    public User saveUser(User user) {
        log.info("saving user {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving role {}", role);
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("adding role {} to user {}", roleName, email);
        User user = userRepository.findByEmail(email);
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String email) {
        log.info("getting email {}", email);
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> getUsers() {
        log.info("getting all users");
        return userRepository.findAll();
    }

    @Override
    public boolean checkIfUserExist(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User register(User user) throws Exception {
        if (checkIfUserExist(user.getEmail())) {
            throw new Exception("User already exist");
        }
        return saveUser(user);
    }

}
