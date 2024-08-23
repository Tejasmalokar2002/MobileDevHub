package com.example.MobileDevHub.repository;

import com.example.MobileDevHub.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
