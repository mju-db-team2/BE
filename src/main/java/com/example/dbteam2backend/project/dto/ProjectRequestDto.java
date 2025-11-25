package com.example.dbteam2backend.project.dto;

import com.example.dbteam2backend.project.entity.Project;
import com.example.dbteam2backend.project.entity.ProjectStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ProjectRequestDto {
    private Long clientId;
    private String projectCode;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;

    public Project toEntity() {
        return Project.builder()
                .clientId(clientId)
                .projectCode(projectCode)
                .projectName(projectName)
                .startDate(startDate)
                .endDate(endDate)
                .status(status)
                .build();
    }
}
