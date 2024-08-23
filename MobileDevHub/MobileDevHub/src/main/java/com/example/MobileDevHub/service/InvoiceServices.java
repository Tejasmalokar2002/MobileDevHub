package com.example.MobileDevHub.service;

import com.example.MobileDevHub.dto.InvoiceDTO;
import com.example.MobileDevHub.entity.Invoice;
import com.example.MobileDevHub.entity.Order;
import com.example.MobileDevHub.exception.ResourceNotFoundException;
import com.example.MobileDevHub.mapper.InvoiceMapper;
import com.example.MobileDevHub.repository.InvoiceRepository;
import com.example.MobileDevHub.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServices {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private OrderRepository orderRepository;

    public List<InvoiceDTO> findAll() {
        return invoiceRepository.findAll().stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<InvoiceDTO> findById(Long id) {
        return invoiceRepository.findById(id)
                .map(invoiceMapper::toDTO);
    }

    public InvoiceDTO save(InvoiceDTO invoiceDTO) {
        Invoice invoice = invoiceMapper.toEntity(invoiceDTO);
        Order order = orderRepository.findById(invoiceDTO.getOrderId())
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + invoiceDTO.getOrderId()));
        invoice.setOrder(order);
        Invoice savedInvoice = invoiceRepository.save(invoice);
        return invoiceMapper.toDTO(savedInvoice);
    }

    public Optional<InvoiceDTO> update(Long id, InvoiceDTO invoiceDTO) {
        return invoiceRepository.findById(id).map(existingInvoice -> {
            Invoice updatedInvoice = invoiceMapper.updateEntityFromDTO(invoiceDTO, existingInvoice);
            Order order = orderRepository.findById(invoiceDTO.getOrderId())
                    .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + invoiceDTO.getOrderId()));
            updatedInvoice.setOrder(order);
            return invoiceMapper.toDTO(invoiceRepository.save(updatedInvoice));
        });
    }

    public void deleteById(Long id) {
        invoiceRepository.deleteById(id);
    }
}
