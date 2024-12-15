package com.example.GoShare.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.GoShare.security.CustomAuthenticationEntryPoint;
import com.example.GoShare.security.CustomeAccessDeniedHandler;
import com.example.GoShare.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    private final String[] PUBLIC_ENDPOINTS = {
            "/api/auth/login", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-resources/**",
            "/webjars/**"
    };

    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    private CustomeAccessDeniedHandler customeAccessDeniedHandler;

    private JwtAuthenticationFilter jwtAuthenticationFilter;

    /**
     * <p>
     * Configures Spring Security settings.
     * </p>
     * 
     * <p>
     * This method defines the security filter chain for the application.
     * It disables CSRF, allows public access to specific endpoints, and configures
     * custom authentication handling and filters.
     * </p>
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
            authorize.requestMatchers(PUBLIC_ENDPOINTS).permitAll();
            authorize.requestMatchers("/employee/**").hasRole("ADMIN");
            authorize.anyRequest().authenticated();
        });

        // Config customize error response
        http.exceptionHandling(exception -> exception
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .accessDeniedHandler(customeAccessDeniedHandler));

        // Add a custom authentication filter before the standard username/password
        // filter
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
