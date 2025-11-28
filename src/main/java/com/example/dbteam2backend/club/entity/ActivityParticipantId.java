package com.example.dbteam2backend.club.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ActivityParticipantId implements Serializable {

    @Column(name = "ACTIVITY_ID")
    private Integer activityId;

    @Column(name = "EMP_NO")
    private Integer empNo;
}
