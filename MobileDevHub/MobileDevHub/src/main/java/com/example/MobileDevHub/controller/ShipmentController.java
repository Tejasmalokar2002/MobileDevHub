package com.example.MobileDevHub.controller;

import com.example.MobileDevHub.dto.ShipmentDTO;
import com.example.MobileDevHub.entity.Shipment;
import com.example.MobileDevHub.service.ShipmentService;
import com.example.MobileDevHub.mapper.ShipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/shipment")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    @Autowired
    private ShipmentMapper shipmentMapper;

    @GetMapping("/get")
    public List<ShipmentDTO> getAllShipments() {
        List<Shipment> shipments = shipmentService.findAll();
        return shipments.stream()
                .map(shipmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ShipmentDTO> getShipmentById(@PathVariable Long id) {
        Optional<Shipment> shipment = shipmentService.findById(id);
        return shipment.map(s -> ResponseEntity.ok(shipmentMapper.toDTO(s)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public ResponseEntity<ShipmentDTO> createShipment(@RequestBody ShipmentDTO shipmentDTO) {
        if (shipmentDTO.getOrderId() == null) {
            return ResponseEntity.badRequest().body(null); // Order must not be null
        }
        Shipment createdShipment = shipmentService.save(shipmentDTO);
        return new ResponseEntity<>(shipmentMapper.toDTO(createdShipment), HttpStatus.CREATED);
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<ShipmentDTO> updateShipment(@PathVariable Long id, @RequestBody ShipmentDTO shipmentDTO) {
        if (!shipmentService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        shipmentDTO.setId(id); // Ensure DTO ID is set
        Shipment updatedShipment = shipmentService.update(id, shipmentDTO);
        return ResponseEntity.ok(shipmentMapper.toDTO(updatedShipment));
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        if (shipmentService.findById(id).isPresent()) {
            shipmentService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
