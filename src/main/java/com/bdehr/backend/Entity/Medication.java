package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="medication_table")
public class Medication {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="patient_id")
    private String patientId;

    @Column(name="age")
    private int age;

    @Column(name="gender")
    private String gender;

    @Column(name="hospital_id")
    private String hospitalId;

    @Column(name="doctor_id")
    private String doctorId;

    @Column(name="created_at")
    private String createdAt;

    @Column(name="is_done") // yes, no
    private String isDone;

    @Column(name="form", columnDefinition = "TEXT")
    private String form;

    @Column(name="medication_url")
    private String medicationUrl;

    @Column(name="diagnosis_url")
    private String diagnosisUrl;

    @Column(name="hospital_name")
    private String hospitalName;

    @Column(name="doctor_name")
    private String doctorName;

    @Column(name="problem")
    private String problem;

    public Medication(String patientId, int age, String gender, String hospitalId, String doctorId, String createdAt, String isDone, String form, String hospitalName, String doctorName) {
        this.patientId = patientId;
        this.age = age;
        this.gender = gender;
        this.hospitalId = hospitalId;
        this.doctorId = doctorId;
        this.createdAt = createdAt;
        this.isDone = isDone;
        this.form = form;
        this.hospitalName = hospitalName;
        this.doctorName = doctorName;
    }
}
