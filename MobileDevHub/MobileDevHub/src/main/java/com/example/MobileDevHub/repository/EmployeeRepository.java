package com.example.MobileDevHub.repository;

import com.example.MobileDevHub.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
