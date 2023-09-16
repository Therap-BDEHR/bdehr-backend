package com.bdehr.backend.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "h2p_map")
public class H2PMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="hospital_id")
    private int hospital_id;
    @Column(name="patient_id")
    private int patient_id;
    @Column(name="status")
    private String status;
    @Column(name="admit_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date admin_date;

    public H2PMap() {
    }

    public H2PMap(int hospital_id, int patient_id, String status) {
        this.hospital_id = hospital_id;
        this.patient_id = patient_id;
        this.status = status;
    }

    public int getHospital_id() {
        return hospital_id;
    }

    public void setHospital_id(int hospital_id) {
        this.hospital_id = hospital_id;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAdmin_date() {
        return admin_date;
    }

    public void setAdmin_date(Date admin_date) {
        this.admin_date = admin_date;
    }
}
