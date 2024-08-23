package com.example.MobileDevHub.repository;

import com.example.MobileDevHub.entity.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturesRepository extends JpaRepository<Manufacturer, Long> {
}
