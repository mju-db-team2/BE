package com.example.dbteam2backend.employee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmployeeRequestDto {
    private String employeeName;
    private Integer deptId;
    private Integer positionId;
    private Integer statusId;
    private String employeeEmail;
    private String employeePhone;
}
