package com.example.dbteam2backend.club.controller;


import com.example.dbteam2backend.club.dto.ClubDetailResponse;
import com.example.dbteam2backend.club.dto.ClubListResponse;
import com.example.dbteam2backend.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @GetMapping
    public List<ClubListResponse> listClubs(){
        return clubService.getAllClubs();
    }

    @GetMapping("/{clubId}")
    public ClubDetailResponse getDetail(@PathVariable Integer clubId){
        return clubService.getClubDetail(clubId);
    }
}
