package com.example.dbteam2backend.club.dto;

import com.example.dbteam2backend.club.entity.ClubStatus;

public record ClubCreateResponse(
        Integer clubId,
        String clubName,
        String leaderName,
        Integer memberCount,
        ClubStatus clubStatus
) {
}
