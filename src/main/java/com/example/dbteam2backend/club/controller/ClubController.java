package com.example.dbteam2backend.club.controller;


import com.example.dbteam2backend.club.dto.ClubDetailResponse;
import com.example.dbteam2backend.club.dto.ClubListResponse;
import com.example.dbteam2backend.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clubs")
public class ClubController {

    private final ClubService clubService;

    @GetMapping
    public ResponseEntity<List<ClubListResponse>> listClubs(){
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @GetMapping("/{clubId}")
    public ResponseEntity<ClubDetailResponse> getDetail(@PathVariable Integer clubId){
        return ResponseEntity.ok(clubService.getClubDetail(clubId));
    }
}
