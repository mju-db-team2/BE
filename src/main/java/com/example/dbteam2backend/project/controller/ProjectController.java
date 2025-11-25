package com.example.dbteam2backend.project.controller;

import com.example.dbteam2backend.project.dto.ProjectResponseDto;
import com.example.dbteam2backend.project.dto.ProjectStatusResponseDto;
import com.example.dbteam2backend.project.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public ResponseEntity<List<ProjectResponseDto>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @GetMapping("/{projectId}/status")
    public ResponseEntity<ProjectStatusResponseDto> getProjectStatus(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectStatus(projectId));
    }
    @PostMapping
    public ResponseEntity<Long> createProject(@RequestBody com.example.dbteam2backend.project.dto.ProjectRequestDto requestDto) {
        return ResponseEntity.ok(projectService.createProject(requestDto));
    }
    @PostMapping("/assignments")
    public ResponseEntity<Long> assignDeveloper(@RequestBody com.example.dbteam2backend.project.dto.AssignmentRequestDto requestDto) {
        return ResponseEntity.ok(projectService.assignDeveloper(requestDto));
    }
    
    @GetMapping("/{projectId}/statistics")
    public ResponseEntity<com.example.dbteam2backend.project.dto.ProjectStatisticsDto> getProjectStatistics(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectStatistics(projectId));
    }

    @GetMapping("/dashboard")
    public ResponseEntity<com.example.dbteam2backend.project.dto.DashboardDto> getDashboardStatistics() {
        return ResponseEntity.ok(projectService.getDashboardStatistics());
    }
}

