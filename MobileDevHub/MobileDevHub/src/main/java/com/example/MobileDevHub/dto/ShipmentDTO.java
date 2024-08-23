package com.example.MobileDevHub.dto;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShipmentDTO {

    private Long id;
    private Long shipmentNumber;
    private Long orderId; // Reference to Order ID
    private LocalDate shipmentDate;
    private LocalDate deliveryDate;
    private String status;

    // Getters and Setters
}
