package com.example.MobileDevHub.repository;

import com.example.MobileDevHub.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
