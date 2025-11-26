package com.example.dbteam2backend.employee.repository;

import com.example.dbteam2backend.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
