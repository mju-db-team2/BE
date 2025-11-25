package com.example.dbteam2backend.project.dto;

import com.example.dbteam2backend.project.entity.ProjectStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDto {
    private Map<ProjectStatus, Long> projectCountByStatus;
    private Long totalProjects;
    private Long totalDevelopers;
}
