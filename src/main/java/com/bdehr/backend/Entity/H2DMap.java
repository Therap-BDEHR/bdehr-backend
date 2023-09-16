package com.bdehr.backend.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "h2d_map")
public class H2DMap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="hospital_id")
    private int hospital_id;
    @Column(name="doctor_id")
    private int doctor_id;

    public H2DMap() {
    }

    public H2DMap(int hospital_id, int doctor_id) {
        this.hospital_id = hospital_id;
        this.doctor_id = doctor_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
