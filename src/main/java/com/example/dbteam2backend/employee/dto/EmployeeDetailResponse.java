package com.example.dbteam2backend.employee.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class EmployeeDetailResponse {

    //  EMPLOYEE 테이블 전체 컬럼 -> 다필요할까?
    private Integer empNo;
    private Integer statusId;
    private Integer positionId;
    private Integer deptId;

    private String employeeName;
    private String employeeResidentNumber;
    private String employeeEducation;
    private LocalDate employeeDate;
    private String employeePhone;
    private String employeeEmail;
    private String employeeType;


    private List<SkillInfo> skills;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class SkillInfo {
        private Integer skillId;
        private String skillName;
        private Integer profLevel;
        private BigDecimal expYears;
        private LocalDate lastUsedDate;
        private Boolean isPrimary;
    }
}
