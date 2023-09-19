package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "h2d_map")
public class H2DMap{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="hospital_id")
    private int hospitalId;
    @Column(name="doctor_id")
    private int doctorId;

    @Column(name="address")
    private String address;

    public H2DMap(int hospital_id, int doctorId) {
        this.hospitalId = hospital_id;
        this.doctorId = doctorId;
    }
    public H2DMap(int hospital_id, int doctorId, String address) {
        this.hospitalId = hospital_id;
        this.doctorId = doctorId;
        this.address = address;
    }
}
