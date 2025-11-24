package com.example.dbteam2backend.club.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLUB_ACTIVITY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACTIVITY_ID")
    private Integer activityId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLUB_ID")
    private Club club;

    @Column(name = "ACTIVITY_DATE")
    private LocalDateTime activityDate;

    @Column(name = "ACTIVITY_LOCATION")
    private String activityLocation;

    @Column(name = "ACTIVITY_SUMMARY")
    private String activitySummary;

    @Column(name = "ACTIVITY_STATUS")
    private Integer activityStatus;

    @OneToMany(mappedBy = "activity",fetch = FetchType.LAZY)
    @Builder.Default
    private List<ActivityParticipant> participants = new ArrayList<>();
}
