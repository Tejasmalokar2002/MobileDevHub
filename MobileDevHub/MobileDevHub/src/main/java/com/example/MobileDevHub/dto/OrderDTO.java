package com.example.MobileDevHub.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDTO {

    private Long id;
    private LocalDate orderDate;
    private Long quantity;
    private Long totalPrice;
    private String status;
    private Long mobileId; // Changed from mobile_id to mobileId
    private Long supplierId; // Changed from supplier_id to supplierId
}
