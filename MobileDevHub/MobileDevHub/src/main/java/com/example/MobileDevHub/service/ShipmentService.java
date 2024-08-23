package com.example.MobileDevHub.service;

import com.example.MobileDevHub.dto.ShipmentDTO;
import com.example.MobileDevHub.entity.Order;
import com.example.MobileDevHub.entity.Shipment;
import com.example.MobileDevHub.mapper.ShipmentMapper;
import com.example.MobileDevHub.repository.OrderRepository;
import com.example.MobileDevHub.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {


    @Autowired
    private ShipmentRepository shipmentRepository;

    @Autowired
    private ShipmentMapper shipmentMapper;

    @Autowired
    private OrderRepository orderRepository; // Add this if not already present

    public List<Shipment> findAll() {
        return shipmentRepository.findAll();
    }

    public Optional<Shipment> findById(Long id) {
        return shipmentRepository.findById(id);
    }

    public Shipment save(ShipmentDTO shipmentDTO) {
        Order order = orderRepository.findById(shipmentDTO.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        Shipment shipment = shipmentMapper.toEntity(shipmentDTO);
        shipment.setOrder(order);

        return shipmentRepository.save(shipment);
    }

    public Shipment update(Long id, ShipmentDTO shipmentDTO) {
        if (shipmentRepository.existsById(id)) {
            Shipment shipment = shipmentMapper.toEntity(shipmentDTO);
            shipment.setId(id); // Ensure ID is set for update
            return shipmentRepository.save(shipment);
        }
        return null; // Or throw an exception if needed
    }


    public void deleteById(Long id) {
        shipmentRepository.deleteById(id);
    }
}