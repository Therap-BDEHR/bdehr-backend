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

    @Column(name="user_id")
    private String userId;

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

    @Column(name="form")
    private String form;

    @Column(name="url")
    private String url;

    public Medication(String userId, int age, String gender, String hospitalId, String doctorId, String createdAt, String form, String url) {
        this.userId = userId;
        this.age = age;
        this.gender = gender;
        this.hospitalId = hospitalId;
        this.doctorId = doctorId;
        this.createdAt = createdAt;
        this.form = form;
        this.url = url;
    }
}
