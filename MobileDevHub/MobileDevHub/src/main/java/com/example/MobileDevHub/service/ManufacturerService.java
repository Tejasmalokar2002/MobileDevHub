package com.example.MobileDevHub.service;

import com.example.MobileDevHub.entity.Manufacturer;
import com.example.MobileDevHub.repository.ManufacturesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerService {

    @Autowired
    private ManufacturesRepository manufacturesRepository;

    public List<Manufacturer> findAll() {
        return manufacturesRepository.findAll();
    }

    public Optional<Manufacturer> findById(Long id) {
        return manufacturesRepository.findById(id);
    }

    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturesRepository.save(manufacturer);
    }

    public Manufacturer update(Long id, Manufacturer manufacturer) {
        if (manufacturesRepository.existsById(id)) {
            manufacturer.setId(id);
            return manufacturesRepository.save(manufacturer);
        }
        return null; // Or throw an exception if needed
    }

    public void deleteById(Long id) {
        manufacturesRepository.deleteById(id);
    }
}

