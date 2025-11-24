package com.example.dbteam2backend.employee.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeSkillFilterRequest {

    //  기술 이름 리스트
    private List<String> skillNames;

    // 선택 조건
    private Integer minProfLevel;   //1, 2, 3,
    private BigDecimal minExpYears;     // 최소 경험 연차
    private LocalDate lastUsedAfter;    // 이 날짜 이후에 사용한 사람만
}
