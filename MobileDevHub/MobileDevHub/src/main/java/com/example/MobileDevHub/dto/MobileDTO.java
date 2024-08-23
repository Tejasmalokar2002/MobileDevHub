package com.example.MobileDevHub.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MobileDTO {

    private Long mobile_id;
    private String mobileName;
    private String brand;
    private Long price;
    private LocalDate releaseDate;
    private String specification;
    private String quantityInStock;
    private LocalDate manufacturingDate;
    private LocalDate warrantyPeriod;
    private Long manufacturerId; // Reference to Manufacturer ID
    private List<MobileDTO> mobiles;
    // Getters and Setters
}
