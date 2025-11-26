package com.example.dbteam2backend.project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RoleStatisticsDto {
    private Long roleId;
    private Integer headcount;
    private BigDecimal totalAllocation;
    private Double ratio;
}
