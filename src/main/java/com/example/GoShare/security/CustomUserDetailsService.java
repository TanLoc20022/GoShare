package com.example.GoShare.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.GoShare.model.Employee;
import com.example.GoShare.repository.EmployeeRepository;

import jakarta.transaction.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

        private EmployeeRepository employeeRepository;

        /**
         * Find the user from database
         */
        @Override
        public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

                Employee employee = employeeRepository.findByUserName(userName)
                                .orElseThrow(() -> new UsernameNotFoundException(
                                                "User not exists by Username"));

                Set<GrantedAuthority> authorities = employee.getEmployeeRole().stream()
                                .map(userRole -> new SimpleGrantedAuthority(userRole.getRoleId().getRoleName()))
                                .collect(Collectors.toSet());

                return new User(
                                userName,
                                employee.getPassWord(),
                                authorities);
        }
}