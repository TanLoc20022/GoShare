package com.example.GoShare.service;

import com.example.GoShare.dto.request.EmployeeRequest;
import com.example.GoShare.model.Employee;

public interface EmployeeService {
    public void registerUser(EmployeeRequest request);

    public void checkDuplicateInfoUser(EmployeeRequest request);

    public Employee getUserByUserName(String userName);

    public Employee getUserByEmail(String email);

    public Employee getUserByPhoneNumber(String phoneNumber);

    public boolean existsByUserName(String userName);

    public boolean existsByEmail(String email);

    public boolean existsByPhoneNumber(String phoneNumber);
}
