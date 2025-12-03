package com.example.dbteam2backend.employee.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DepartmentRequestDto {
    private String deptName;
    private String deptCode;
    private String status;
}
