package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.EmployeeDTO;
import com.example.MobileDevHub.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDTO toDTO(Employee employee);
    Employee toEntity(EmployeeDTO employeeDTO);

    @Mapping(target = "id", ignore = true) // ignore the id if you don't want to change it
    Employee updateEntityFromDTO(EmployeeDTO employeeDTO, @MappingTarget Employee employee);
}

