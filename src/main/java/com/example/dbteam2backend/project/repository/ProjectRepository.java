package com.example.dbteam2backend.project.repository;

import com.example.dbteam2backend.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
