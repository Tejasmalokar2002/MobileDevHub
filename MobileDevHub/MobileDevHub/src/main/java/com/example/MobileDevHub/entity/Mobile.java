package com.example.MobileDevHub.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Mobile")
public class Mobile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long mobile_id;


    private String mobileName;

    private String brand;


    private Long price;

    private LocalDate releaseDate;

    private String specification;

    private String quantityInStock;

    private LocalDate manufacturingDate;


    private LocalDate warrantyPeriod;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @OneToMany(mappedBy = "mobile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();


    public Long getMobile_id() {
        return mobile_id;
    }

}
