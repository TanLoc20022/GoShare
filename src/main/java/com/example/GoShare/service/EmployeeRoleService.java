package com.example.GoShare.service;

import com.example.GoShare.model.Employee;
import com.example.GoShare.model.Role;

public interface EmployeeRoleService {
    void createUserRole(Employee user, Role role);
}
