package com.example.dbteam2backend.employee.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STATUS_CODE")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatusCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STATUS_ID")
    private Integer statusId;

    @Column(name = "STATUS_SITUATION", length = 45)
    private String statusSituation;
}
