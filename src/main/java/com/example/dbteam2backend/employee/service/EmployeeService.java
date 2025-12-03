package com.example.dbteam2backend.employee.service;

import com.example.dbteam2backend.employee.dto.EmployeeDetailResponse;
import com.example.dbteam2backend.employee.dto.EmployeeSkillFilterRequest;
import com.example.dbteam2backend.employee.entity.Employee;
import com.example.dbteam2backend.employee.entity.EmployeeSkill;
import com.example.dbteam2backend.employee.repository.EmployeeSkillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EmployeeService {

    private final EmployeeSkillRepository employeeSkillRepository;

    public List<EmployeeDetailResponse> filterEmployeesBySkills(EmployeeSkillFilterRequest request) {
        if (request.getSkillNames() == null || request.getSkillNames().isEmpty()) {
            throw new IllegalArgumentException("skillNames는 필수입니다.");
        }

        List<Employee> employees =
                employeeSkillRepository.findEmployeesBySkillsAndFilters(
                        request.getSkillNames(),
                        request.getSkillNames().size(),
                        request.getMinProfLevel(),
                        request.getMinExpYears(),
                        request.getLastUsedAfter()
                );

        return employees.stream()
                .map(e -> EmployeeDetailResponse.builder()
                        .empNo(e.getEmpNo())
                        .statusId(e.getStatusId())
                        .positionId(e.getPositionId())
                        .deptId(e.getDeptId())
                        .employeeName(e.getEmployeeName())
                        .employeeResidentNumber(e.getEmployeeResidentNumber())
                        .employeeEducation(e.getEmployeeEducation())
                        .employeeDate(e.getEmployeeDate())
                        .employeePhone(e.getEmployeePhone())
                        .employeeEmail(e.getEmployeeEmail())
                        .employeeType(e.getEmployeeType())


                        .skills(
                                e.getEmployeeSkills().stream()
                                        .sorted(Comparator.comparing(es -> es.getSkill().getSkillName()))
                                        .map(this::toSkillInfo)
                                        .collect(Collectors.toList())
                        )
                        .build())
                .collect(Collectors.toList());
    }

    private EmployeeDetailResponse.SkillInfo toSkillInfo(EmployeeSkill es) {
        return EmployeeDetailResponse.SkillInfo.builder()
                .skillId(es.getSkill().getSkillId())
                .skillName(es.getSkill().getSkillName())
                .profLevel(es.getProfLevel())
                .expYears(es.getExpYears())
                .lastUsedDate(es.getLastUsedDate())
                .isPrimary(es.getIsPrimary())
                .build();
    }
    private final com.example.dbteam2backend.employee.repository.DepartmentRepository departmentRepository;
    private final com.example.dbteam2backend.employee.repository.PositionRepository positionRepository;
    private final com.example.dbteam2backend.employee.repository.StatusCodeRepository statusCodeRepository;
    private final com.example.dbteam2backend.employee.repository.EmployeeRepository employeeRepository;

    @Transactional
    public com.example.dbteam2backend.employee.entity.Department createDepartment(com.example.dbteam2backend.employee.dto.DepartmentRequestDto requestDto) {
        return departmentRepository.save(com.example.dbteam2backend.employee.entity.Department.builder()
                .deptName(requestDto.getDeptName())
                .deptCode(requestDto.getDeptCode())
                .status(requestDto.getStatus())
                .build());
    }

    @Transactional
    public com.example.dbteam2backend.employee.entity.Position createPosition(com.example.dbteam2backend.employee.dto.PositionRequestDto requestDto) {
        return positionRepository.save(com.example.dbteam2backend.employee.entity.Position.builder()
                .positionName(requestDto.getPositionName())
                .build());
    }

    @Transactional
    public com.example.dbteam2backend.employee.entity.StatusCode createStatusCode(com.example.dbteam2backend.employee.dto.StatusCodeRequestDto requestDto) {
        return statusCodeRepository.save(com.example.dbteam2backend.employee.entity.StatusCode.builder()
                .statusSituation(requestDto.getStatusSituation())
                .build());
    }

    @Transactional
    public Employee createEmployee(com.example.dbteam2backend.employee.dto.EmployeeRequestDto requestDto) {
        return employeeRepository.save(Employee.builder()
                .employeeName(requestDto.getEmployeeName())
                .deptId(requestDto.getDeptId())
                .positionId(requestDto.getPositionId())
                .statusId(requestDto.getStatusId())
                .employeeEmail(requestDto.getEmployeeEmail())
                .employeePhone(requestDto.getEmployeePhone())
                .build());
    }
}
