package com.example.dbteam2backend.employee.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class EmployeeSkillId implements Serializable {

    @Column(name = "EMP_NO")
    private Integer empNo;

    @Column(name = "SKILL_ID")
    private Integer skillId;
}
