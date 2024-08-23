package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.EmployeeDTO;
import com.example.MobileDevHub.dto.ManufacturerDTO;
import com.example.MobileDevHub.entity.Employee;
import com.example.MobileDevHub.entity.Manufacturer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ManufacturerMapper {
    ManufacturerDTO toDTO(Manufacturer manufacturer);
    Manufacturer toEntity(ManufacturerDTO manufacturerDTO);


    @Mapping(target = "id", ignore = true) // ignore the id if you don't want to change it
    Manufacturer updateEntityFromDTO(ManufacturerDTO manufacturerDTO, @MappingTarget Manufacturer manufacturer);

}
