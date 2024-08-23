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
@Table(name = "Shipment")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Long shipmentNumber;

    @ManyToOne
    @JoinColumn(name = "orderId", nullable = false)
    private Order order;


    private LocalDate shipmentDate;

    private LocalDate deliveryDate;


    private String status;


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
