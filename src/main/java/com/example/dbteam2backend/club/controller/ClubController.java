package com.example.dbteam2backend.club.controller;

import com.example.dbteam2backend.club.dto.ClubCreateRequest;
import com.example.dbteam2backend.club.dto.ClubCreateResponse;
import com.example.dbteam2backend.club.dto.ClubDetailResponse;
import com.example.dbteam2backend.club.dto.ClubListResponse;
import com.example.dbteam2backend.club.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

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

    @PostMapping
    public ResponseEntity<ClubCreateResponse> createClub(@Valid @RequestBody ClubCreateRequest request) {
        return ResponseEntity.ok(clubService.createClub(request));
    }
}
