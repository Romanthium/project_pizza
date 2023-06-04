package com.example.projectpizza.config.security;

import com.example.projectpizza.model.UserRole;
import com.example.projectpizza.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder = httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService());
        AuthenticationManager authenticationManager = authenticationManagerBuilder.build();

        httpSecurity
//                .csrf()
//                .disable()
                .authenticationManager(authenticationManager)
                .authorizeHttpRequests()
                .requestMatchers("/", "/cafes/**").hasAnyRole(UserRole.ADMIN.name(),
                        UserRole.GLOBAL_MANAGER.name(),
                        UserRole.CAFE_MANAGER.name())
                .requestMatchers("/dish-types/**",
                        "/dishes/**",
                        "/ingredients/**",
                        "/units/**").hasAnyRole(UserRole.ADMIN.name(), UserRole.GLOBAL_MANAGER.name())
                .requestMatchers("/users/**").hasRole(UserRole.ADMIN.name())
                .requestMatchers("/auth/login", "/error", "auth/access-denied", "/bootstrap/**") //white list
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/", true)
                .failureUrl("/auth/login?error")
                .and()
                .logout().logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/auth/access-denied");

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userService
                .findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
