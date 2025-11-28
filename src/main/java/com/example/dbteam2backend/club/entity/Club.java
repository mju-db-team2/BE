package com.example.dbteam2backend.club.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLUB")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLUB_ID")
    private Integer clubId;

    @Column(name = "CLUB_NAME")
    private String clubName;

    @Enumerated(EnumType.STRING)
    @Column(name = "CLUB_STATUS")
    private ClubStatus clubStatus; //ACTIVE, INACTIVE

    @Column(name = "CLUB_NUM")
    private Integer clubNum;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ClubRole> roles = new ArrayList<>();

    @OneToMany(mappedBy = "club", fetch = FetchType.LAZY)
    @Builder.Default
    private List<ClubActivity> activities = new ArrayList<>();
}
