package com.example.MobileDevHub.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SupplierDTO {

    private Long supplier_id;
    private String name;
    private Long contactNumber;
    private String email;
    private String address;
    private LocalDate supplyDate;

    // Getters and Setters
}
