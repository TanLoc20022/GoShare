package com.example.GoShare.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Employee_Role", uniqueConstraints = @UniqueConstraint(columnNames = { "EmployeeID", "RoleID" }))
public class EmployeeRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "EmployeeID")
    private Employee employeeId;

    @ManyToOne
    @JoinColumn(name = "RoleID")
    private Role roleId;

    @NotNull
    @Column(name = "AssignedDate")
    private LocalDate assignedDate;

    @NotNull
    @Column(name = "Status")
    private String status;

}