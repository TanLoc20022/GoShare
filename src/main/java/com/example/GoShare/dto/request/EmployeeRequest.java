package com.example.GoShare.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private String userName;
    private String passWord;
    private String phoneNumber;
    private String email;
    private Integer roleID;
}