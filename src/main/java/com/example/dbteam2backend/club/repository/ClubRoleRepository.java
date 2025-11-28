package com.example.dbteam2backend.club.repository;

import com.example.dbteam2backend.club.entity.ClubRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClubRoleRepository extends JpaRepository<ClubRole, Integer> {
}
