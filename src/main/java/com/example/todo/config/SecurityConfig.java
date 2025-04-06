package com.example.todo.config;

import com.example.todo.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

   @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
       return http
               .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
               .csrf(csrf -> csrf.disable())
               .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers("/api/auth/**").permitAll()
//                       .requestMatchers("/api/users/**").permitAll()
                       .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                       .anyRequest().authenticated()
               )
               .build();
   }

   @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }

   @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
       return config.getAuthenticationManager();
   }
}
