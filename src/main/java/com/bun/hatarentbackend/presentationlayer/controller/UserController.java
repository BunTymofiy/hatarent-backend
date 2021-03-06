package com.bun.hatarentbackend.presentationlayer.controller;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.bun.hatarentbackend.userservice.domain.*;
import com.bun.hatarentbackend.userservice.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;



    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/user")
    public ResponseEntity<UserPasswordLessDTO> getUser(@AuthenticationPrincipal User user) {
        User foundUser = userService.getUser(user.getEmail());
        UserPasswordLessDTO userPasswordLessDTO = userMapper.toPasswordLessDTO(foundUser);
        return ResponseEntity.ok().body(userPasswordLessDTO);
    }
    @GetMapping("/user/info/{uuid}")
    public ResponseEntity<UserNameAndEmailDTO> getUserNameAndEmail(@PathVariable UUID uuid) {
        User user = userService.getUserByUuid(uuid);
        return ResponseEntity.ok().body(userMapper.toNameAndEmailDTO(user));
    }
    @RequestMapping(method = RequestMethod.HEAD , value = "/logout")
    public ResponseEntity<Void> logout()   {
        return ResponseEntity.noContent()
                .header(HttpHeaders.SET_COOKIE, ResponseCookie.from("token", "")
                        .httpOnly(true)
                        .domain("hatarent-backend.herokuapp.com")
                        .sameSite("None")
                        .secure(true)
                        .maxAge(0)
                        .path("/")
                        .build().toString())
                .build();
    }

    @PostMapping("/users/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form)
    {
        userService.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @PostMapping("user/register/host")
    public ResponseEntity<User> registerHost(@RequestBody User user) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/register/host").toUriString());
        User newUser = userService.register(user);
        userService.addRoleToUser(newUser.getEmail(), "ROLE_HOST");
        return ResponseEntity.created(uri).body(newUser);
    }
    @PostMapping("user/register/guest")
    public ResponseEntity<User> registerGuest(@RequestBody User user) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/register/guest").toUriString());
        User newUser = userService.register(user);
        userService.addRoleToUser(newUser.getEmail(), "ROLE_GUEST");
        return ResponseEntity.created(uri).body(newUser);
    }

//    @GetMapping("/token/refresh")
//    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException
//    {
//        String authorizationHeader = request.getHeader(AUTHORIZATION);
//        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
//            try{
//                String refresh_token = authorizationHeader.substring("Bearer ".length());
//                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
//                JWTVerifier verifier = JWT.require(algorithm).build();
//                DecodedJWT decodedJWT = verifier.verify(refresh_token);
//                String username = decodedJWT.getSubject();
//                User user = userService.getUser(username);
//
//                String access_token = JWT.create()
//                        .withSubject(user.getUsername())
//                        .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
//                        .withIssuer(request.getRequestURL().toString())
//                        .withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
//                        .sign(algorithm);
//
//                Map<String,String> tokens = new HashMap<>();
//                tokens.put("access_token", access_token);
//                tokens.put("refresh_token", refresh_token);
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
//
//            }
//            catch (Exception e){
//                log.error("Error logging in: {}", e.getMessage());
//                response.setHeader("error", e.getMessage());
//                response.setStatus(FORBIDDEN.value());
////                    response.sendError(FORBIDDEN.value());
//                Map<String,String> error = new HashMap<>();
//                error.put("error_message", e.getMessage());
//                response.setContentType(APPLICATION_JSON_VALUE);
//                new ObjectMapper().writeValue(response.getOutputStream(), error);
//            }
//        }
//        else
//        {
//            throw new RuntimeException("Refresh token is missing");
//        }
//
//    }
}
@Data
class RoleToUserForm
{
    private String username;
    private String roleName;
}
