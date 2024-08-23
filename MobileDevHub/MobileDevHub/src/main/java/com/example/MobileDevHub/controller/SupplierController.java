package com.example.MobileDevHub.controller;

import com.example.MobileDevHub.dto.SupplierDTO;
import com.example.MobileDevHub.entity.Supplier;
import com.example.MobileDevHub.service.SupplierService;
import com.example.MobileDevHub.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @Autowired
    private SupplierMapper supplierMapper;

    @GetMapping("/get")
    public List<SupplierDTO> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.findAll();
        return suppliers.stream()
                .map(supplierMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@PathVariable Long id) {
        Optional<Supplier> supplier = supplierService.findById(id);
        return supplier.map(s -> ResponseEntity.ok(supplierMapper.toDTO(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = supplierMapper.toEntity(supplierDTO);
        Supplier createdSupplier = supplierService.save(supplier);
        return new ResponseEntity<>(supplierMapper.toDTO(createdSupplier), HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<SupplierDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierDTO supplierDTO) {
        Optional<Supplier> existingSupplier = supplierService.findById(id);
        if (existingSupplier.isPresent()) {
            Supplier supplier = existingSupplier.get();
            supplierMapper.updateEntityFromDTO(supplierDTO, supplier);
            Supplier updatedSupplier = supplierService.save(supplier);
            return ResponseEntity.ok(supplierMapper.toDTO(updatedSupplier));
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
        if (supplierService.findById(id).isPresent()) {
            supplierService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
