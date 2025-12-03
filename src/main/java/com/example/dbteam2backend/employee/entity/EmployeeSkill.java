package com.example.dbteam2backend.employee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "EMPLOYEE_SKILL")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeSkill {

    @EmbeddedId
    private EmployeeSkillId id;

    @MapsId("empNo")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMP_NO")
    private Employee employee;

    @MapsId("skillId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SKILL_ID")
    private Skill skill;

    @Column(name = "PROF_LEVEL")
    private Integer profLevel;

    @Column(name = "EXP_YEARS")
    private BigDecimal expYears;

    @Column(name = "LAST_USED_DATE")
    private LocalDate lastUsedDate;

    @Column(name = "IS_PRIMARY")
    private Boolean isPrimary;
}
