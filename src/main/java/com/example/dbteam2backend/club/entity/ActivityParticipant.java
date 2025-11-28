package com.example.dbteam2backend.club.entity;

import com.example.dbteam2backend.employee.entity.Employee;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name = "ACTIVITY_PARTICIPANT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityParticipant {

    @EmbeddedId
    private ActivityParticipantId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("activityId")
    @JoinColumn(name = "ACTIVITY_ID")
    private ClubActivity activity;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("empNo")
    @JoinColumn(name = "EMP_NO")
    private Employee employee;

    @Column(name = "ATTENDANCE_STATUS")
    private Integer attendanceStatus;

    @Column(name = "SCORE")
    private BigDecimal score;

}
