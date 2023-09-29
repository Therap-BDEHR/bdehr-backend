package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Integer> {
    Report findById(String id);
    List<Report> findByPatientId(String patientId);
}
