package com.example.GoShare.model;

import java.util.Set;
import java.util.UUID;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "EmployeeID")
    private UUID employeeId;

    @Column(name = "UserName", length = 50, unique = true)
    @Size(min = 8, max = 50)
    @NonNull
    private String userName;

    @NonNull
    @Column(name = "Password")
    @Size(min = 8, max = 50)
    private String passWord;

    @NonNull
    @Column(name = "PhoneNumber", length = 15, unique = true)
    @Size(min = 10, max = 15)
    private String phoneNumber;

    @Column(name = "Email", length = 50, unique = true)
    @Email
    private String email;

    @OneToMany(mappedBy = "employeeId")
    private Set<EmployeeRole> employeeRole;
}