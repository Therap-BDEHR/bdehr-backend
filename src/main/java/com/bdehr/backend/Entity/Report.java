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
    @Column(name="id")
    private String id;

    @Column(name="user_id")
    private String userId;

    @Column(name="hospital_id")
    private String hospitalId;

    @Column(name="doctor_id")
    private String doctorId;

    @Column(name="created_at",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdAt;

    @Column(name="report")
    private String report;

    public Report(String userId, String hospitalId, String doctorId, String report) {
        this.userId = userId;
        this.hospitalId = hospitalId;
        this.doctorId = doctorId;
        this.report = report;
    }
}
