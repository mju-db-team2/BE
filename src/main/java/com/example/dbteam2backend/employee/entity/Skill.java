package com.example.dbteam2backend.employee.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SKILL")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @Column(name = "SKILL_ID")
    private Integer skillId;

    @Column(name = "SKILL_NAME")
    private String skillName;

    @Column(name = "SKILL_CATEGORY")
    private String skillCategory;   // ENUM(.) 이라 일단 String 처리

    @Column(name = "SKILL_DESCRIPT")
    private String skillDescript;

    @Column(name = "SKILL_STATUS")
    private String skillStatus;     // ENUM(.) 이라 일단 String 처리

    @OneToMany(mappedBy = "skill", fetch = FetchType.LAZY)
    @Builder.Default
    private List<EmployeeSkill> employeeSkills = new ArrayList<>();
}
