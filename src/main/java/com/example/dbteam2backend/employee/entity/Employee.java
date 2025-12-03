package com.example.dbteam2backend.employee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "EMPLOYEE")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMP_NO")
    private Integer empNo;

    @Column(name = "STATUS_ID")
    private Integer statusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID", insertable = false, updatable = false)
    private StatusCode statusCode;

    @Column(name = "POSITION_ID")
    private Integer positionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_ID", insertable = false, updatable = false)
    private Position position;

    @Column(name = "DEPT_ID")
    private Integer deptId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPT_ID", insertable = false, updatable = false)
    private Department department;

    @Column(name = "EMPLOYEE_NAME")
    private String employeeName;

    @Column(name = "EMPLOYEE_RESIDENT_NUMBER")
    private String employeeResidentNumber;

    @Column(name = "EMPLOYEE_EDUCATION")
    private String employeeEducation;

    @Column(name = "EMPLOYEE_DATE")
    private LocalDate employeeDate;

    @Column(name = "EMPLOYEE_PHONE")
    private String employeePhone;

    @Column(name = "EMPLOYEE_EMAIL")
    private String employeeEmail;

    @Column(name = "EMPLOYEE_TYPE")
    private String employeeType;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @Builder.Default
    private List<EmployeeSkill> employeeSkills = new ArrayList<>();
}
