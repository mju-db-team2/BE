package com.example.dbteam2backend.project.service;

import com.example.dbteam2backend.project.dto.AssignmentDto;
import com.example.dbteam2backend.project.dto.ProjectResponseDto;
import com.example.dbteam2backend.project.dto.ProjectStatusResponseDto;
import com.example.dbteam2backend.project.entity.Project;
import com.example.dbteam2backend.project.repository.AssignmentRepository;
import com.example.dbteam2backend.project.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final AssignmentRepository assignmentRepository;

    public List<ProjectResponseDto> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(ProjectResponseDto::new)
                .collect(Collectors.toList());
    }

    public ProjectStatusResponseDto getProjectStatus(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));

        List<AssignmentDto> assignments = assignmentRepository.findByProject_ProjectId(projectId).stream()
                .map(AssignmentDto::new)
                .collect(Collectors.toList());

        return new ProjectStatusResponseDto(project, assignments);
    }
    @Transactional
    public Long createProject(com.example.dbteam2backend.project.dto.ProjectRequestDto requestDto) {
        Project project = requestDto.toEntity();
        projectRepository.save(project);
        return project.getProjectId();
    }
    @Transactional
    public Long assignDeveloper(com.example.dbteam2backend.project.dto.AssignmentRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + requestDto.getProjectId()));

        com.example.dbteam2backend.project.entity.Assignment assignment = requestDto.toEntity(project);
        assignmentRepository.save(assignment);
        return assignment.getAssignmentId();
    }
    
    public com.example.dbteam2backend.project.dto.ProjectStatisticsDto getProjectStatistics(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found with id: " + projectId));

        List<com.example.dbteam2backend.project.entity.Assignment> assignments = 
                assignmentRepository.findByProject_ProjectId(projectId);

        // 역할별 그룹핑 및 집계
        Map<Long, List<com.example.dbteam2backend.project.entity.Assignment>> groupedByRole = 
                assignments.stream()
                        .collect(Collectors.groupingBy(com.example.dbteam2backend.project.entity.Assignment::getRoleId));

        // 전체 allocation 합계
        java.math.BigDecimal totalAllocation = assignments.stream()
                .map(com.example.dbteam2backend.project.entity.Assignment::getAllocation)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);

        // 역할별 통계 생성
        List<com.example.dbteam2backend.project.dto.RoleStatisticsDto> roleStats = groupedByRole.entrySet().stream()
                .map(entry -> {
                    Long roleId = entry.getKey();
                    List<com.example.dbteam2backend.project.entity.Assignment> roleAssignments = entry.getValue();
                    
                    int headcount = roleAssignments.size();
                    java.math.BigDecimal roleAllocation = roleAssignments.stream()
                            .map(com.example.dbteam2backend.project.entity.Assignment::getAllocation)
                            .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
                    
                    double ratio = totalAllocation.compareTo(java.math.BigDecimal.ZERO) > 0 
                            ? roleAllocation.divide(totalAllocation, 4, java.math.RoundingMode.HALF_UP).doubleValue()
                            : 0.0;
                    
                    return new com.example.dbteam2backend.project.dto.RoleStatisticsDto(
                            roleId, headcount, roleAllocation, ratio);
                })
                .collect(Collectors.toList());

        return new com.example.dbteam2backend.project.dto.ProjectStatisticsDto(
                projectId, 
                project.getProjectName(),
                assignments.size(),
                totalAllocation,
                roleStats
        );
    }

    public com.example.dbteam2backend.project.dto.DashboardDto getDashboardStatistics() {
        List<Project> allProjects = projectRepository.findAll();
        
        // 상태별 프로젝트 수 집계
        Map<com.example.dbteam2backend.project.entity.ProjectStatus, Long> countByStatus = 
                allProjects.stream()
                        .collect(Collectors.groupingBy(Project::getStatus, Collectors.counting()));
        
        // 전체 투입된 개발자 수 (중복 제거)
        Long totalDevelopers = assignmentRepository.findAll().stream()
                .map(com.example.dbteam2backend.project.entity.Assignment::getDeveloperId)
                .distinct()
                .count();
        
        return new com.example.dbteam2backend.project.dto.DashboardDto(
                countByStatus,
                (long) allProjects.size(),
                totalDevelopers
        );
    }
}

