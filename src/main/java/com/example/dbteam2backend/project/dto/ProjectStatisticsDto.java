package com.example.dbteam2backend.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectStatisticsDto {
    private Long projectId;
    private String projectName;
    private Integer totalDevelopers;
    private BigDecimal totalAllocation;
    private List<RoleStatisticsDto> roles;
}
