package com.example.dbteam2backend.club.dto;


import com.example.dbteam2backend.club.entity.ClubStatus;

import java.time.LocalDateTime;

public record ClubListResponse(
        Integer clubId,
        String clubName,
        String leaderName,
        Integer memberCount,
        ClubStatus clubStatus,
        LocalDateTime createdAt
) {
}
