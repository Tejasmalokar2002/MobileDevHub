package com.example.MobileDevHub.repository;

import com.example.MobileDevHub.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
