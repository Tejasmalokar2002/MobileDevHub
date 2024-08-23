package com.example.MobileDevHub.controller;

import com.example.MobileDevHub.dto.ManufacturerDTO;
import com.example.MobileDevHub.entity.Manufacturer;
import com.example.MobileDevHub.service.ManufacturerService;
import com.example.MobileDevHub.mapper.ManufacturerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manufacturer")
public class ManufacturerController {

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ManufacturerMapper manufacturerMapper;

    @GetMapping("/get")
    public List<ManufacturerDTO> getAllManufacturers() {
        return manufacturerService.findAll().stream()
                .map(manufacturerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ManufacturerDTO> getManufacturerById(@PathVariable Long id) {
        Optional<Manufacturer> manufacturer = manufacturerService.findById(id);
        return manufacturer.map(manufacturerMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<ManufacturerDTO> createManufacturer(@RequestBody ManufacturerDTO manufacturerDTO) {
        Manufacturer createdManufacturer = manufacturerService.save(manufacturerMapper.toEntity(manufacturerDTO));
        ManufacturerDTO createdManufacturerDTO = manufacturerMapper.toDTO(createdManufacturer);
        return new ResponseEntity<>(createdManufacturerDTO, HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<ManufacturerDTO> updateManufacturer(@PathVariable Long id, @RequestBody ManufacturerDTO manufacturerDTO) {
        Optional<Manufacturer> updatedManufacturer = manufacturerService.findById(id)
                .map(existingManufacturer -> {
                    manufacturerMapper.updateEntityFromDTO(manufacturerDTO, existingManufacturer);
                    return manufacturerService.save(existingManufacturer);
                });

        return updatedManufacturer.map(manufacturerMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteManufacturer(@PathVariable Long id) {
        if (manufacturerService.findById(id).isPresent()) {
            manufacturerService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
