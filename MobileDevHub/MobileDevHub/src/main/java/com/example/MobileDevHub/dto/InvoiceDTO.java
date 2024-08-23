package com.example.MobileDevHub.dto;

import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InvoiceDTO {

    private Long id;
    private Long invoiceNumber;
    private Long orderId; // Reference to Order ID
    private LocalDate invoiceDate;
    private Long totalAmount;
    private String paymentStatus;

    // Getters and Setters
}
