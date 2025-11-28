package com.example.dbteam2backend.project.dto;

import com.example.dbteam2backend.project.entity.Project;
import com.example.dbteam2backend.project.entity.ProjectStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class ProjectResponseDto {
    private Long projectId;
    private String projectCode;
    private String projectName;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus status;
    private String clientName;

    public ProjectResponseDto(Project project) {
        this.projectId = project.getProjectId();
        this.projectCode = project.getProjectCode();
        this.projectName = project.getProjectName();
        this.startDate = project.getStartDate();
        this.endDate = project.getEndDate();
        this.status = project.getStatus();
        if (project.getClient() != null) {
            this.clientName = project.getClient().getClientName();
        }
    }
}
