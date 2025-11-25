package com.example.dbteam2backend.project.dto;

import com.example.dbteam2backend.project.entity.Assignment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class AssignmentDto {
    private Long developerId;
    private Long roleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal allocation;

    public AssignmentDto(Assignment assignment) {
        this.developerId = assignment.getDeveloperId();
        this.roleId = assignment.getRoleId();
        this.startDate = assignment.getStartDate();
        this.endDate = assignment.getEndDate();
        this.allocation = assignment.getAllocation();
    }
}
