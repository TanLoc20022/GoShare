package com.example.GoShare.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.GoShare.dto.request.EmployeeRequest;
import com.example.GoShare.dto.response.ApiResponse;
import com.example.GoShare.service.EmployeeService;

import lombok.AllArgsConstructor;



@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ApiResponse<?> createUser(@RequestBody EmployeeRequest request) {
        employeeService.registerUser(request);

        return ApiResponse
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message("Tạo User Thành công")
                .build();
    }
}