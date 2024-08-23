package com.example.MobileDevHub.dto;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManufacturerDTO {

    private Long id;
    private String name;
    private String address;
    private Long contactNumber;
    private String email;
    private LocalDate establishedDate;

    // Getters and Setters
}
