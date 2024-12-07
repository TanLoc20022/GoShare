package com.example.GoShare.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GoShare.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByUserName(String userName);

    Optional<Employee> findByUserName(String username);

    Optional<Employee> findByPhoneNumber(String phoneNumber);

    Optional<Employee> findByEmail(String email);
}