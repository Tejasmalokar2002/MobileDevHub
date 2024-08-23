package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.EmployeeDTO;
import com.example.MobileDevHub.dto.SupplierDTO;
import com.example.MobileDevHub.entity.Employee;
import com.example.MobileDevHub.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    @Mapping(source = "supplier_id", target = "supplier_id")
    SupplierDTO toDTO(Supplier supplier);

    @Mapping(source = "supplier_id", target = "supplier_id")
    Supplier toEntity(SupplierDTO supplierDTO);


    @Mapping(target = "supplier_id", ignore = true) // ignore the id if you don't want to change it
    Supplier updateEntityFromDTO(SupplierDTO supplierDTO, @MappingTarget Supplier supplier);
}
