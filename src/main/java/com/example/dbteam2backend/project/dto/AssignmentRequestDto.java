package com.example.dbteam2backend.project.dto;

import com.example.dbteam2backend.project.entity.Assignment;
import com.example.dbteam2backend.project.entity.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class AssignmentRequestDto {
    private Long projectId;
    private Integer developerId;
    private Long roleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal allocation;

    public Assignment toEntity(Project project) {
        return Assignment.builder()
                .project(project)
                .developerId(developerId)
                .roleId(roleId)
                .startDate(startDate)
                .endDate(endDate)
                .allocation(allocation)
                .build();
    }
}
