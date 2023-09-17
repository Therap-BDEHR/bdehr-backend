package com.bdehr.backend.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="medication_table")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private int user_id;

    @Column(name="hospital_id")
    private int hospital_id;

    @Column(name="doctor_id")
    private int doctor_id;

    @Column(name="created_at",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date created_at;

    @Column(name="prescription")
    private String prescription;

    public Medication() {
    }

    public Medication(int user_id, int hospital_id, int doctor_id, String prescription) {
        this.user_id = user_id;
        this.hospital_id = hospital_id;
        this.doctor_id = doctor_id;
        this.prescription = prescription;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public void setDoctor_id(int doctor_id) {
        this.doctor_id = doctor_id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }
}