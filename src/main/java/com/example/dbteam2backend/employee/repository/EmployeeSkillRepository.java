package com.example.dbteam2backend.employee.repository;

import com.example.dbteam2backend.employee.entity.Employee;
import com.example.dbteam2backend.employee.entity.EmployeeSkill;
import com.example.dbteam2backend.employee.entity.EmployeeSkillId;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface EmployeeSkillRepository extends JpaRepository<EmployeeSkill, EmployeeSkillId> {

    /*
     AND 조건 스킬 필터링 (요청한 모든 스킬을 가진 직원만)
     + 선택조건(minProfLevel, minExpYears, lastUsedAfter) 적용
     */
    @Query(value = """
        SELECT e.*
        FROM EMPLOYEE e
        JOIN EMPLOYEE_SKILL es ON e.EMP_NO = es.EMP_NO
        JOIN SKILL s ON es.SKILL_ID = s.SKILL_ID
        WHERE s.SKILL_NAME IN (:skillNames)
          AND (:minProfLevel IS NULL OR es.PROF_LEVEL >= :minProfLevel)
          AND (:minExpYears IS NULL OR es.EXP_YEARS >= :minExpYears)
          AND (:lastUsedAfter IS NULL OR es.LAST_USED_DATE >= :lastUsedAfter)
        GROUP BY e.EMP_NO
        HAVING COUNT(DISTINCT s.SKILL_ID) = :skillCount
        """,
            nativeQuery = true)
    List<Employee> findEmployeesBySkillsAndFilters(
            @Param("skillNames") List<String> skillNames,
            @Param("skillCount") long skillCount,
            @Param("minProfLevel") Integer minProfLevel,
            @Param("minExpYears") BigDecimal minExpYears,
            @Param("lastUsedAfter") LocalDate lastUsedAfter
    );
}
