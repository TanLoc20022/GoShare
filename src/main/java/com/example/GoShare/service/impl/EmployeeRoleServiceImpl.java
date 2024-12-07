package com.example.GoShare.service.impl;

import java.time.LocalDate;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.GoShare.model.Employee;
import com.example.GoShare.model.EmployeeRole;
import com.example.GoShare.model.Role;
import com.example.GoShare.repository.EmployeeRoleRepository;
import com.example.GoShare.service.EmployeeRoleService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeRoleServiceImpl implements EmployeeRoleService {

    private final EmployeeRoleRepository employeeRoleRepository;

    @Override
    public void createUserRole(Employee employee, Role role) {
        if (employeeRoleRepository.existsByEmployeeIdAndRoleId(employee, role)) {
            throw new DataIntegrityViolationException("Already have ");
        }

        EmployeeRole userRole = EmployeeRole
                .builder()
                .employeeId(employee)
                .roleId(role)
                .assignedDate(LocalDate.now())
                .status("ACTIVE")
                .build();

        employeeRoleRepository.save(userRole);
    }

}
