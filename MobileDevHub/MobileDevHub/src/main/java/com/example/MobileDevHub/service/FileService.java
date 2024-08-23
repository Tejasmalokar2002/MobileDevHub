package com.example.MobileDevHub.service;

import com.example.MobileDevHub.entity.*;
import com.example.MobileDevHub.entity.FileUpload;
import com.example.MobileDevHub.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Service
public class FileService {

    @Autowired
    private FileUploadRepository fileUploadRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ManufacturesRepository manufacturesRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private MobileRepository mobileRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ShipmentRepository shipmentRepository;

    public String uploadFile(MultipartFile file, Long entityId, String entityType) {
        String fileName = file.getOriginalFilename();
        String fileContent;

        try {
            fileContent = new String(file.getBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read file content", e);
        }

        FileUpload fileUpload = new FileUpload();
        fileUpload.setFileName(fileName);
        fileUpload.setFileContent(fileContent);
        fileUpload.setUploadTime(LocalDateTime.now());

        switch (entityType.toLowerCase()) {
            case "employee":
                Employee employee = employeeRepository.findById(entityId)
                        .orElseThrow(() -> new RuntimeException("Employee not found"));
                fileUpload.setEmployee(employee);
                break;
            case "manufacturer":
                Manufacturer manufacturer = manufacturesRepository.findById(entityId)
                        .orElseThrow(() -> new RuntimeException("Manufacturer not found"));
                fileUpload.setManufacturer(manufacturer);
                break;
            case "supplier":
                Supplier supplier = supplierRepository.findById(entityId)
                        .orElseThrow(() -> new RuntimeException("Supplier not found"));
                fileUpload.setSupplier(supplier);
                break;
            case "mobile":
                Mobile mobile = mobileRepository.findById(entityId)
                        .orElseThrow(() -> new RuntimeException("Mobile not found"));
                fileUpload.setMobile(mobile);
                break;
            case "order":
                Order order = orderRepository.findById(entityId)
                        .orElseThrow(() -> new RuntimeException("Order not found"));
                fileUpload.setOrder(order);
                break;
            case "invoice":
                Invoice invoice = invoiceRepository.findById(entityId)
                        .orElseThrow(() -> new RuntimeException("Invoice not found"));
                fileUpload.setInvoice(invoice);
                break;
            case "shipment":
                Shipment shipment = shipmentRepository.findById(entityId)
                        .orElseThrow(() -> new RuntimeException("Shipment not found"));
                fileUpload.setShipment(shipment);
                break;
            default:
                throw new IllegalArgumentException("Invalid entity type");
        }

        fileUploadRepository.save(fileUpload);
        return "File uploaded successfully: " + fileName;
    }

    public FileUpload getFileByEntity(String entityType, Long entityId) {
        switch (entityType.toLowerCase()) {
            case "employee":
                return fileUploadRepository.findByEmployee_Id(entityId)
                        .orElseThrow(() -> new RuntimeException("File not found for Employee"));
            case "manufacturer":
                return fileUploadRepository.findByManufacturer_Id(entityId)
                        .orElseThrow(() -> new RuntimeException("File not found for Manufacturer"));
//            case "supplier":
//                return fileUploadRepository.findBySupplier_Id(entityId)
//                        .orElseThrow(() -> new RuntimeException("File not found for Supplier"));
//            case "mobile":
//                return fileUploadRepository.findByMobile_Id(entityId)
//                        .orElseThrow(() -> new RuntimeException("File not found for Mobile"));
            case "order":
                return fileUploadRepository.findByOrder_Id(entityId)
                        .orElseThrow(() -> new RuntimeException("File not found for Order"));
            case "invoice":
                return fileUploadRepository.findByInvoice_Id(entityId)
                        .orElseThrow(() -> new RuntimeException("File not found for Invoice"));
            case "shipment":
                return fileUploadRepository.findByShipment_Id(entityId)
                        .orElseThrow(() -> new RuntimeException("File not found for Shipment"));
            default:
                throw new IllegalArgumentException("Invalid entity type");
        }
    }
}
