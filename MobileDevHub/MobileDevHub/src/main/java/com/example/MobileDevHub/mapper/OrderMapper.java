package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.OrderDTO;
import com.example.MobileDevHub.entity.Mobile;
import com.example.MobileDevHub.entity.Order;
import com.example.MobileDevHub.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // Mapping from DTO to Entity
    @Mapping(target = "mobile", ignore = true) // Ignoring the mobile object in entity mapping
    @Mapping(target = "supplier", ignore = true) // Ignoring the supplier object in entity mapping
    Order toEntity(OrderDTO orderDTO);

    // Mapping from Entity to DTO
    @Mapping(target = "mobileId", source = "mobile", qualifiedByName = "mobileToMobileId") // Custom mapping method
    @Mapping(target = "supplierId", source = "supplier", qualifiedByName = "supplierToSupplierId") // Custom mapping method
    OrderDTO toDTO(Order order);

    // Mapping for updating an existing entity with DTO data
    @Mapping(target = "id", ignore = true) // Ignore the id field
    @Mapping(target = "mobile", ignore = true) // Ignore the mobile object in entity mapping
    @Mapping(target = "supplier", ignore = true) // Ignore the supplier object in entity mapping
    Order updateEntityFromDTO(OrderDTO orderDTO, @MappingTarget Order order);

    // Custom mapping methods to extract the IDs from Mobile and Supplier objects
    @Named("mobileToMobileId")
    default Long mobileToMobileId(Mobile mobile) {
        return mobile != null ? mobile.getMobile_id() : null;
    }

    @Named("supplierToSupplierId")
    default Long supplierToSupplierId(Supplier supplier) {
        return supplier != null ? supplier.getSupplier_id() : null;
    }
}
