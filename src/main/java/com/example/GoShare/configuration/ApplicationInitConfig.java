package com.example.GoShare.configuration;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.GoShare.dto.request.EmployeeRequest;
import com.example.GoShare.enums.RoleEnum;
import com.example.GoShare.model.Role;
import com.example.GoShare.service.EmployeeService;
import com.example.GoShare.service.RoleService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class ApplicationInitConfig {

    private final EmployeeService employeeService;
    private final RoleService roleService;

    @Bean
    @Transactional
    ApplicationRunner applicationRunner() {
        return args -> {
            if (!roleService.existsByRoleName(RoleEnum.ADMIN.name())
                    && !employeeService.existsByUserName(RoleEnum.ADMIN.name())) {

                Role role = roleService.addRole(RoleEnum.ADMIN.name());

                EmployeeRequest employeeRequest = EmployeeRequest.builder().userName(RoleEnum.ADMIN.name())
                        .passWord(RoleEnum.ADMIN.name()).roleID(role.getRoleId()).build();

                employeeService.registerUser(employeeRequest);
            }
        };
    }
}
