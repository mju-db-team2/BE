package com.example.dbteam2backend.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity                         // 이 클래스가 DB 테이블과 매핑된다는 뜻
@Table(name = "PROJECT")        // 실제 테이블 이름(대문자 그대로)
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PROJECT_ID INT AUTO_INCREMENT 같은 느낌
    @Column(name = "PROJECT_ID")    // 컬럼명 그대로
    private Long projectId;         // 자바에서 쓸 필드명 (CamelCase)

    @Column(name = "CLIENT_ID")     // CLIENT_ID INT
    private Long clientId;

    @Column(name = "PROJECT_CODE", length = 30) // VARCHAR(30)
    private String projectCode;

    @Column(name = "PROJECT_NAME", length = 100) // VARCHAR(100)
    private String projectName;

    @Column(name = "START_DATE")    // DATE → LocalDate
    private LocalDate startDate;

    @Column(name = "END_DATE")      // DATE → LocalDate
    private LocalDate endDate;

    // STATUS ENUM(...)인데, 일단 간단히 String으로 매핑
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private ProjectStatus status;

    @org.hibernate.annotations.CreationTimestamp
    @Column(name = "CREATED_AT", updatable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "project")
    private List<Assignment> assignments = new ArrayList<>();
}
