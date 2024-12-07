package com.example.GoShare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.GoShare.model.Employee;
import com.example.GoShare.model.EmployeeRole;
import com.example.GoShare.model.Role;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long> {
    boolean existsByEmployeeIdAndRoleId(Employee employee, Role role);
}