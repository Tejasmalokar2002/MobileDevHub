package com.example.MobileDevHub.mapper;

import com.example.MobileDevHub.dto.EmployeeDTO;
import com.example.MobileDevHub.entity.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-23T05:57:24-0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDTO toDTO(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId( employee.getId() );
        employeeDTO.setName( employee.getName() );
        employeeDTO.setDesignation( employee.getDesignation() );
        employeeDTO.setContactNumber( employee.getContactNumber() );
        employeeDTO.setEmail( employee.getEmail() );
        employeeDTO.setHireDate( employee.getHireDate() );

        return employeeDTO;
    }

    @Override
    public Employee toEntity(EmployeeDTO employeeDTO) {
        if ( employeeDTO == null ) {
            return null;
        }

        Employee employee = new Employee();

        employee.setId( employeeDTO.getId() );
        employee.setName( employeeDTO.getName() );
        employee.setDesignation( employeeDTO.getDesignation() );
        employee.setContactNumber( employeeDTO.getContactNumber() );
        employee.setEmail( employeeDTO.getEmail() );
        employee.setHireDate( employeeDTO.getHireDate() );

        return employee;
    }

    @Override
    public Employee updateEntityFromDTO(EmployeeDTO employeeDTO, Employee employee) {
        if ( employeeDTO == null ) {
            return employee;
        }

        employee.setName( employeeDTO.getName() );
        employee.setDesignation( employeeDTO.getDesignation() );
        employee.setContactNumber( employeeDTO.getContactNumber() );
        employee.setEmail( employeeDTO.getEmail() );
        employee.setHireDate( employeeDTO.getHireDate() );

        return employee;
    }
}
