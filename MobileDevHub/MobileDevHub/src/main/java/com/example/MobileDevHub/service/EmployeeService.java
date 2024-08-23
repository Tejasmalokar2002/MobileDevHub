package com.example.MobileDevHub.service;

import com.example.MobileDevHub.dto.EmployeeDTO;
import com.example.MobileDevHub.entity.Employee;
import com.example.MobileDevHub.exception.ResourceNotFoundException;
import com.example.MobileDevHub.mapper.EmployeeMapper;
import com.example.MobileDevHub.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<EmployeeDTO> findById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDTO);
    }

    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        Employee savedEmployee = employeeRepository.save(employee);
        return employeeMapper.toDTO(savedEmployee);
    }

    public Optional<EmployeeDTO> update(Long id, EmployeeDTO employeeDTO) {
        return employeeRepository.findById(id).map(existingEmployee -> {
            Employee updatedEmployee = employeeMapper.updateEntityFromDTO(employeeDTO, existingEmployee);
            return employeeMapper.toDTO(employeeRepository.save(updatedEmployee));
        });
    }

    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }
}
