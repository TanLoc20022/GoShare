package com.example.GoShare.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.GoShare.dto.request.EmployeeRequest;
import com.example.GoShare.model.Employee;
import com.example.GoShare.model.Role;
import com.example.GoShare.repository.EmployeeRepository;
import com.example.GoShare.service.RoleService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import com.example.GoShare.service.EmployeeRoleService;
import com.example.GoShare.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final RoleService roleService;
    private final EmployeeRoleService employeeRoleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void registerUser(EmployeeRequest request) {
        checkDuplicateInfoUser(request);

        Employee user = Employee.builder()
                .passWord(passwordEncoder.encode(request.getPassWord()))
                .userName(request.getUserName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .build();

        Employee userSave = employeeRepository.save(user);

        Role defaultRole = roleService.getRoleById(request.getRoleID());

        employeeRoleService.createUserRole(userSave, defaultRole);

    }

    /**
     * Check all field have unique in table users.
     * Field check: UserName, PhoneNumber, Email
     * 
     * @param request The value in request need to check
     */
    public void checkDuplicateInfoUser(EmployeeRequest request) {
        Map<String, Boolean> duplicateList = new LinkedHashMap<>();
        duplicateList.put("UserName", employeeRepository.existsByUserName(request.getUserName()));
        duplicateList.put("PhoneNumber", employeeRepository.existsByPhoneNumber(request.getPhoneNumber()));
        duplicateList.put("Email", employeeRepository.existsByEmail(request.getEmail()));
        duplicateList.forEach((fieldName, exists) -> {
            if (exists) {
                throw new DataIntegrityViolationException("Already have " + fieldName);
            }
        });
    }

    @Override
    public Employee getUserByUserName(String userName) {
        return employeeRepository.findByUserName(userName).get();
    }

    @Override
    public Employee getUserByEmail(String email) {
        return employeeRepository.findByEmail(email).get();
    }

    @Override
    public Employee getUserByPhoneNumber(String phoneNumber) {
        return employeeRepository.findByPhoneNumber(phoneNumber).get();
    }

    @Override
    public boolean existsByUserName(String userName) {
        return employeeRepository.existsByUserName(userName);
    }

    @Override
    public boolean existsByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByPhoneNumber(String phoneNumber) {
        return employeeRepository.existsByPhoneNumber(phoneNumber);
    }

}