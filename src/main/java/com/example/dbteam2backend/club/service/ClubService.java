package com.example.dbteam2backend.club.service;

import com.example.dbteam2backend.club.dto.ClubCreateRequest;
import com.example.dbteam2backend.club.dto.ClubCreateResponse;
import com.example.dbteam2backend.club.dto.ClubDetailResponse;
import com.example.dbteam2backend.club.dto.ClubListResponse;
import com.example.dbteam2backend.club.entity.Club;
import com.example.dbteam2backend.club.entity.ClubRole;
import com.example.dbteam2backend.club.entity.ClubRoleType;
import com.example.dbteam2backend.club.entity.ClubStatus;
import com.example.dbteam2backend.club.repository.ClubRepository;
import com.example.dbteam2backend.club.repository.ClubRoleRepository;
import com.example.dbteam2backend.employee.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.dbteam2backend.employee.repository.EmployeeRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClubService {

    private final ClubRepository clubRepository;
    private final ClubRoleRepository clubRoleRepository;
    private final EmployeeRepository employeeRepository;

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

    @Transactional
    public ClubCreateResponse createClub(ClubCreateRequest request) {
        Club club = Club.builder()
                .clubName(request.clubName())
                .clubStatus(ClubStatus.ACTIVE)
                .clubNum(1)
                .createdAt(LocalDateTime.now())
                .build();

        Club savedClub = clubRepository.save(club);

        //리더 조회
        Employee leader = employeeRepository.findById(request.leaderEmpNo())
                .orElseThrow(()->new RuntimeException("존재하지 않는 직원입니다."));

        //leader 역할 등록
        ClubRole leaderRole = ClubRole.builder()
                .club(savedClub)
                .employee(leader)
                .role(ClubRoleType.LEADER)
                .build();
        clubRoleRepository.save(leaderRole);

        return new ClubCreateResponse(
                savedClub.getClubId(),
                savedClub.getClubName(),
                leader.getEmployeeName(),
                1,
                savedClub.getClubStatus()
        );
    }
}
