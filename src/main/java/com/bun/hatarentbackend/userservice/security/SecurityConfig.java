package com.bun.hatarentbackend.userservice.security;

import com.bun.hatarentbackend.userservice.filter.CustomAuthenticationFilter;
import com.bun.hatarentbackend.userservice.filter.CustomAuthorizationFilter;
import com.bun.hatarentbackend.userservice.filter.CustomFilter;
import com.bun.hatarentbackend.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserRepo userRepo;
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/login");
        http.cors().configurationSource(corsConfigurationSource());
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(GET, "/user").authenticated();
//        http.authorizeRequests().antMatchers(DELETE, "/property/**").permitAll();
//        http.authorizeRequests().antMatchers(POST, "/property/**").hasAnyAuthority("ROLE_HOST");
        http.authorizeRequests().antMatchers("/login/**", "/token/refresh/**", "/user/register/**", "/user/info/**","/property/byCity/**", "/property/**", "/logout", "/nights/**", "/reservation/**","/reservation/property/**", "/invoice/**", "/transaction/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/property").permitAll();

        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
//        http.addFilterBefore(new CORSFilter(), ChannelProcessingFilter.class);

    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        final DaoAuthenticationConfigurer dao = new DaoAuthenticationConfigurer<>(userRepo::findByEmail);

        dao.passwordEncoder(bCryptPasswordEncoder);
        auth.apply(dao);
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://hatarent-frontend.vercel.app"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","HEAD"));
        configuration.setAllowedHeaders(Collections.singletonList("*"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
