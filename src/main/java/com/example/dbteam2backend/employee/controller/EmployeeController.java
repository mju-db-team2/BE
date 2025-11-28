package com.example.dbteam2backend.employee.controller;

import com.example.dbteam2backend.employee.dto.EmployeeDetailResponse;
import com.example.dbteam2backend.employee.dto.EmployeeSkillFilterRequest;
import com.example.dbteam2backend.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/filter")
    public List<EmployeeDetailResponse> filterEmployees(@RequestBody EmployeeSkillFilterRequest request) {
        return employeeService.filterEmployeesBySkills(request);
    }

    @PostMapping("/departments")
    public com.example.dbteam2backend.employee.entity.Department createDepartment(@RequestBody com.example.dbteam2backend.employee.dto.DepartmentRequestDto requestDto) {
        return employeeService.createDepartment(requestDto);
    }

    @PostMapping("/positions")
    public com.example.dbteam2backend.employee.entity.Position createPosition(@RequestBody com.example.dbteam2backend.employee.dto.PositionRequestDto requestDto) {
        return employeeService.createPosition(requestDto);
    }

    @PostMapping("/status-codes")
    public com.example.dbteam2backend.employee.entity.StatusCode createStatusCode(@RequestBody com.example.dbteam2backend.employee.dto.StatusCodeRequestDto requestDto) {
        return employeeService.createStatusCode(requestDto);
    }

    @PostMapping
    public com.example.dbteam2backend.employee.entity.Employee createEmployee(@RequestBody com.example.dbteam2backend.employee.dto.EmployeeRequestDto requestDto) {
        return employeeService.createEmployee(requestDto);
    }
}
