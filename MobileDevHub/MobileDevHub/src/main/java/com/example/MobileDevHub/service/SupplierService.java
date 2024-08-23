package com.example.MobileDevHub.service;

import com.example.MobileDevHub.entity.Invoice;
import com.example.MobileDevHub.entity.Supplier;
import com.example.MobileDevHub.repository.InvoiceRepository;
import com.example.MobileDevHub.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {



    @Autowired
    private SupplierRepository supplierRepository;

    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier update(Long id, Supplier supplier) {
        if (supplierRepository.existsById(id)) {
            supplier.setSupplier_id(id);
            return supplierRepository.save(supplier);
        }
        return null; // Or throw an exception if needed
    }

    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }
}