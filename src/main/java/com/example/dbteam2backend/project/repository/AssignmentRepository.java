package com.example.dbteam2backend.project.repository;

import com.example.dbteam2backend.project.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    List<Assignment> findByProject_ProjectId(Long projectId);
}
