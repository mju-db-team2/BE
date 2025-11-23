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

}
