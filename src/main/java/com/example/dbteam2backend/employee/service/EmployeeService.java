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
                .skillName(es.getSkill().getSkillName())
                .profLevel(es.getProfLevel())
                .expYears(es.getExpYears())
                .lastUsedDate(es.getLastUsedDate())
                .isPrimary(es.getIsPrimary())
                .build();
    }
}
