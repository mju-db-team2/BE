package com.example.dbteam2backend.project.repository;

import com.example.dbteam2backend.project.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    @EntityGraph(attributePaths = {"developer", "role", "developer.department", "developer.position"})
    List<Assignment> findByProject_ProjectId(Long projectId);
}
