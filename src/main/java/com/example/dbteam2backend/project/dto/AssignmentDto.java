package com.example.dbteam2backend.project.dto;

import com.example.dbteam2backend.project.entity.Assignment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class AssignmentDto {
    private Long assignmentId;
    private Long projectId;
    private Integer developerId;
    private Long roleId;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal allocation;
    private String developerName;
    private String roleName;
    private String departmentName;
    private String positionName;

    public AssignmentDto(Assignment assignment) {
        this.assignmentId = assignment.getAssignmentId();
        this.projectId = assignment.getProject().getProjectId();
        this.developerId = assignment.getDeveloperId();
        this.roleId = assignment.getRoleId();
        this.startDate = assignment.getStartDate();
        this.endDate = assignment.getEndDate();
        this.allocation = assignment.getAllocation();
        if (assignment.getDeveloper() != null) {
            this.developerName = assignment.getDeveloper().getEmployeeName();
            if (assignment.getDeveloper().getDepartment() != null) {
                this.departmentName = assignment.getDeveloper().getDepartment().getDeptName();
            }
            if (assignment.getDeveloper().getPosition() != null) {
                this.positionName = assignment.getDeveloper().getPosition().getPositionName();
            }
        }
        if (assignment.getRole() != null) {
            this.roleName = assignment.getRole().getRoleName();
        }
    }
}
