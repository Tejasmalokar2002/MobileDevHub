package com.example.MobileDevHub.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.boot.web.reactive.filter.OrderedWebFilter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "supplier_seq")
    @SequenceGenerator(name = "supplier_seq", sequenceName = "supplier_seq", allocationSize = 1)
    private Long supplier_id;


    private String name;


    private Long contactNumber;


    private String email;


    private String address;

    private LocalDate supplyDate;

//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Order> orders = new ArrayList<>();


    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

//    @ManyToOne
//    @JoinColumn(name = "orderId", nullable = false)
//    private Order order;

}
