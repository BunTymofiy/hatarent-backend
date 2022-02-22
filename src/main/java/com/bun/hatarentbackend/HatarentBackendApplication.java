package com.bun.hatarentbackend;

import com.bun.hatarentbackend.userservice.domain.Role;
import com.bun.hatarentbackend.userservice.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@ComponentScan("com.bun")
public class HatarentBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(HatarentBackendApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(UserService userService) {
//		return args -> {
//			userService.saveRole(new Role(null, "ROLE_GUEST"));
//			userService.saveRole(new Role(null, "ROLE_HOST"));
//		};
//	}
@Bean
PasswordEncoder passwordEncoder()
{
	return new BCryptPasswordEncoder();
}
}
