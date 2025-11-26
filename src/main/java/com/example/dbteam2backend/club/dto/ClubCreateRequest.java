package com.example.dbteam2backend.club.dto;

import com.example.dbteam2backend.club.entity.ClubStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClubCreateRequest(
        @NotBlank(message = "클럽 이름은 필수입니다.")
        String clubName,
        @NotNull(message = "리더는 필수입니다.")
        Long leaderEmpNo
) {
}
