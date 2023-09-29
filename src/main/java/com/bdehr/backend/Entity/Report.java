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

    @Column(name="patient_id")
    private String patientId;

    @Column(name="hospital_id")
    private String hospitalId;

    @Column(name="hospital_name")
    private String hospitalName;

    @Column(name="lab_id")
    private String labId;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="url")
    private String url;

    public Report(String patientId, String hospitalId, String hospitalName, String labId, String createdAt, String url) {
        this.patientId = patientId;
        this.hospitalId = hospitalId;
        this.hospitalName = hospitalName;
        this.labId = labId;
        this.createdAt = createdAt;
        this.url = url;
    }
}
