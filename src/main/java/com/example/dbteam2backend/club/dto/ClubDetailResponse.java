package com.example.dbteam2backend.club.dto;

import com.example.dbteam2backend.club.entity.ClubRoleType;
import com.example.dbteam2backend.club.entity.ClubStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ClubDetailResponse(
        Integer clubId,
        String clubName,
        ClubStatus clubStatus,
        Integer memberCount,
        String leaderName,
        List<MemberInfo> members,
        List<ActivityInfo> activities
) {

    public record MemberInfo(
            Integer empNo,
            String name,
            ClubRoleType role,
            String deptName
    ) {}

    public record ActivityInfo(
            Integer activityId,
            LocalDateTime activityDate,
            String summary,
            String location,
            Integer status,
            List<Integer> participants
    ){}
}
