package com.example.MobileDevHub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Invoice")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long invoiceNumber;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;

    @Column(nullable = false)
    private LocalDate invoiceDate;

    @Column(nullable = false)
    private Long totalAmount;

    @Column(nullable = false)
    private String paymentStatus;
}
