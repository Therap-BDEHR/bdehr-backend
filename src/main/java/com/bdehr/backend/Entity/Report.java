package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="report_table")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int userId;

    @Column(name="hospital_id")
    private int hospitalId;

    @Column(name="doctor_id")
    private int doctorId;

    @Column(name="created_at",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name="report")
    private String report;

    public Report(int userId, int hospitalId, int doctorId, String report) {
        this.userId = userId;
        this.hospitalId = hospitalId;
        this.doctorId = doctorId;
        this.report = report;
    }
}
