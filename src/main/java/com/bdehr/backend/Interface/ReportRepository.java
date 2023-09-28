package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    Report findById(String id);
}
