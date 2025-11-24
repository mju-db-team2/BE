package com.example.dbteam2backend.club.service;

import com.example.dbteam2backend.club.dto.ClubDetailResponse;
import com.example.dbteam2backend.club.dto.ClubListResponse;
import com.example.dbteam2backend.club.entity.Club;
import com.example.dbteam2backend.club.entity.ClubRoleType;
import com.example.dbteam2backend.club.repository.ClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {

    private final ClubRepository clubRepository;

    public List<ClubListResponse> getAllClubs() {
        return clubRepository.findAll().stream()
                .map(club -> {
                    String leaderName = club.getRoles().stream()
                            .filter(role -> role.getRole() == ClubRoleType.LEADER)
                            .findFirst()
                            .map(role -> role.getEmployee().getEmployeeName())
                            .orElse("N/A");

                    return new ClubListResponse(
                            club.getClubId(),
                            club.getClubName(),
                            leaderName,
                            club.getRoles().size(),
                            club.getClubStatus(),
                            club.getCreatedAt()
                    );
                })
                .toList();
    }


    public ClubDetailResponse getClubDetail(Integer clubId) {
        Club club = clubRepository.findById(clubId)
                .orElseThrow(() -> new RuntimeException("Club not found"));

        String leaderName = club.getRoles().stream()
                .filter(role -> role.getRole() == ClubRoleType.LEADER)
                .findFirst()
                .map(role -> role.getEmployee().getEmployeeName())
                .orElse("N/A");

        List<ClubDetailResponse.MemberInfo> members =
                club.getRoles().stream()
                        .map(role -> new ClubDetailResponse.MemberInfo(
                                role.getEmployee().getEmpNo(),
                                role.getEmployee().getEmployeeName(),
                                role.getRole(),
                                String.valueOf(role.getEmployee().getDeptId())
                        ))
                        .toList();

        List<ClubDetailResponse.ActivityInfo> activities =
                club.getActivities().stream()
                        .map(activity -> new ClubDetailResponse.ActivityInfo(
                                activity.getActivityId(),
                                activity.getActivityDate(),
                                activity.getActivitySummary(),
                                activity.getActivityLocation(),
                                activity.getActivityStatus(),
                                activity.getParticipants().stream()
                                        .map(participant -> participant.getEmployee().getEmpNo())
                                        .toList()
                        ))
                        .toList();

        return new ClubDetailResponse(
                club.getClubId(),
                club.getClubName(),
                club.getClubStatus(),
                members.size(),
                leaderName,
                members,
                activities
        );
    }
}

