package com.example.dbteam2backend.project.dto;

import com.example.dbteam2backend.project.entity.Project;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ProjectStatusResponseDto {
    private ProjectResponseDto projectInfo;
    private List<AssignmentDto> assignments;

    public ProjectStatusResponseDto(Project project, List<AssignmentDto> assignments) {
        this.projectInfo = new ProjectResponseDto(project);
        this.assignments = assignments;
    }
}
