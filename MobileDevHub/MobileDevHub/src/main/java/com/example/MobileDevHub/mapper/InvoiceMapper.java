package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.InvoiceDTO;
import com.example.MobileDevHub.entity.Invoice;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InvoiceMapper {

    // Mapping from Entity to DTO
    @Mapping(target = "orderId", source = "order.id")
    InvoiceDTO toDTO(Invoice invoice);

    // Mapping from DTO to Entity
    @Mapping(target = "order", ignore = true) // We will manually set the order in the service layer
    Invoice toEntity(InvoiceDTO invoiceDTO);

    // Mapping for updating an existing entity with DTO data
    @Mapping(target = "id", ignore = true) // Ignore the id if you don't want to change it
    @Mapping(target = "order", ignore = true) // We will manually set the order in the service layer
    Invoice updateEntityFromDTO(InvoiceDTO invoiceDTO, @MappingTarget Invoice invoice);
}
