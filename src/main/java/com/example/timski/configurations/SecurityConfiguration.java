package com.example.timski.configurations;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

//
//    private final JWTAuthenticationFilter jwtAuthFilter;
//    private final AuthenticationProvider authenticationProvider;
//
//    public SecurityConfiguration(JWTAuthenticationFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
//        this.jwtAuthFilter = jwtAuthFilter;
//        this.authenticationProvider = authenticationProvider;
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/home", "/shop-cart", "/assets/**", "/register", "/product", "/api/**", "/login", "/static/**", "/product/add-form", "/product/add", "/payment", "/successfully-payment", "/payment/add", "/categories", "/product/{id}/delete", "/product/{id}/edit-form", "/product/{categoryId}", "/shop-cart/add-product/{id}", "/payment/add", "/successfully-payment").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}