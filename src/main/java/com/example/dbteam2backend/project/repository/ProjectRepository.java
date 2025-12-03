package com.example.dbteam2backend.project.repository;

import com.example.dbteam2backend.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.EntityGraph;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Override
    @EntityGraph(attributePaths = "client")
    List<Project> findAll();
}
