package com.example.MobileDevHub.repository;

import com.example.MobileDevHub.entity.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileUploadRepository extends JpaRepository<FileUpload, Long> {
    Optional<FileUpload> findByEmployee_Id(Long employeeId);
    Optional<FileUpload> findByManufacturer_Id(Long manufacturerId);
   // Optional<FileUpload> findBySupplier_Id(Long supplierId);
//    Optional<FileUpload> findByMobile_Id(Long mobileId);
    Optional<FileUpload> findByOrder_Id(Long orderId);
    Optional<FileUpload> findByInvoice_Id(Long invoiceId);
    Optional<FileUpload> findByShipment_Id(Long shipmentId);
}
