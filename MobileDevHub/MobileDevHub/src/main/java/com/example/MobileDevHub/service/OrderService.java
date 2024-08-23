package com.example.MobileDevHub.service;

import com.example.MobileDevHub.dto.OrderDTO;
import com.example.MobileDevHub.entity.Order;
import com.example.MobileDevHub.exception.ResourceNotFoundException;
import com.example.MobileDevHub.mapper.OrderMapper;
import com.example.MobileDevHub.repository.MobileRepository;
import com.example.MobileDevHub.repository.OrderRepository;
import com.example.MobileDevHub.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private MobileRepository mobileRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    public List<OrderDTO> findAll() {
        return orderRepository.findAll().stream()
                .map(orderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<OrderDTO> findById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::toDTO);
    }

    public OrderDTO save(OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);

        // Fetch and set Mobile and Supplier entities
        order.setMobile(mobileRepository.findById(orderDTO.getMobileId())
                .orElseThrow(() -> new ResourceNotFoundException("Mobile not found")));

        order.setSupplier(supplierRepository.findById(orderDTO.getSupplierId())
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found")));

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder);
    }

    public Optional<OrderDTO> update(Long id, OrderDTO orderDTO) {
        return orderRepository.findById(id).map(existingOrder -> {
            Order order = orderMapper.updateEntityFromDTO(orderDTO, existingOrder);

            // Fetch and set Mobile and Supplier entities
            order.setMobile(mobileRepository.findById(orderDTO.getMobileId())
                    .orElseThrow(() -> new ResourceNotFoundException("Mobile not found")));

            order.setSupplier(supplierRepository.findById(orderDTO.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("Supplier not found")));

            Order updatedOrder = orderRepository.save(order);
            return orderMapper.toDTO(updatedOrder);
        });
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
