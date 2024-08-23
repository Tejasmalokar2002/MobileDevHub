package com.example.MobileDevHub.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    private Long id;
    private String name;
    private String designation;
    private Long contactNumber;
    private String email;
    private LocalDate hireDate;

    // Getters and Setters
}
