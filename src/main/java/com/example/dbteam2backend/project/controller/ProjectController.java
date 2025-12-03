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
    public ResponseEntity<ProjectResponseDto> createProject(@RequestBody com.example.dbteam2backend.project.dto.ProjectRequestDto requestDto) {
        return ResponseEntity.ok(projectService.createProject(requestDto));
    }
    @PostMapping("/assignments")
    public ResponseEntity<com.example.dbteam2backend.project.dto.AssignmentDto> assignDeveloper(@RequestBody com.example.dbteam2backend.project.dto.AssignmentRequestDto requestDto) {
        return ResponseEntity.ok(projectService.assignDeveloper(requestDto));
    }
    
    @PostMapping("/clients")
    public ResponseEntity<com.example.dbteam2backend.project.entity.Client> createClient(@RequestBody com.example.dbteam2backend.project.dto.ClientRequestDto requestDto) {
        com.example.dbteam2backend.project.entity.Client client = com.example.dbteam2backend.project.entity.Client.builder()
                .clientName(requestDto.getClientName())
                .build();
        return ResponseEntity.ok(projectService.createClient(client));
    }

    @PostMapping("/roles")
    public ResponseEntity<com.example.dbteam2backend.project.entity.Role> createRole(@RequestBody com.example.dbteam2backend.project.dto.RoleRequestDto requestDto) {
        com.example.dbteam2backend.project.entity.Role role = com.example.dbteam2backend.project.entity.Role.builder()
                .roleName(requestDto.getRoleName())
                .build();
        return ResponseEntity.ok(projectService.createRole(role));
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

