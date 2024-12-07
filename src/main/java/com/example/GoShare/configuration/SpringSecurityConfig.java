package com.example.GoShare.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.GoShare.security.JwtAuthenticationEntryPoint;
import com.example.GoShare.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    /**
     * Configures Spring Security settings.
     * 
     * This method defines the security filter chain for the application.
     * It disables CSRF, allows public access to specific endpoints, and configures
     * custom authentication handling and filters.
     * 
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception Exception If any error occurs during configuration.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        // Disable csrf
        http.csrf(csrf -> csrf.disable());

        // Configure endpoint access rules
        http.authorizeHttpRequests((authorize) -> {
            authorize.requestMatchers("/api/auth/login").permitAll();
            authorize.requestMatchers("/users/**").hasRole("ADMIN");
            authorize.requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-resources/**",
                    "/webjars/**").permitAll();
            authorize.anyRequest().authenticated();
        });

        // Handle unauthorized access attempts with a custom entry point
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));

        // Add a custom authentication filter before the standard username/password
        // filter
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
