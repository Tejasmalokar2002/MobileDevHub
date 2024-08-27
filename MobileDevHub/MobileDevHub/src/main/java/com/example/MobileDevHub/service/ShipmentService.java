package com.example.MobileDevHub.service;

import com.example.MobileDevHub.dto.ShipmentDTO;
import com.example.MobileDevHub.entity.Order;
import com.example.MobileDevHub.entity.Shipment;
import com.example.MobileDevHub.mapper.ShipmentMapper;
import com.example.MobileDevHub.repository.OrderRepository;
import com.example.MobileDevHub.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService {


    @Autowired
    private ShipmentRepository shipmentRepository;


    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;


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

        sendBookingConfirmationEmail(shipment);
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


    private void sendBookingConfirmationEmail(Shipment shipment) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo("usdreams14@gmail.com"); // Change to actual recipient's email
        message.setSubject("Shipment Confirmation - " + shipment.getShipmentNumber());
        message.setText("Dear Customer,\n\n" +
                "Your shipment has been successfully created with the following details:\n" +
                "Shipment Number: " + shipment.getShipmentNumber() + "\n" +
                "Order ID: " + shipment.getOrder().getId() + "\n" +
                "Shipment Date: " + shipment.getShipmentDate() + "\n" +
                "Delivery Date: " + shipment.getDeliveryDate() + "\n" +
                "Status: " + shipment.getStatus() + "\n\n" +
                "Thank you for using our service!\n\n" +
                "Best regards,\n" +
                "The Team");

        System.out.println("Sending email to: " + message.getTo());
        System.out.println("Email subject: " + message.getSubject());

        try {
            mailSender.send(message);
            System.out.println("Email sent successfully!");
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            System.out.println("Error details: " + e.getMessage());
        }
    }

}