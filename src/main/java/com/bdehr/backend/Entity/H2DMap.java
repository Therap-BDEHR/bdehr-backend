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
    @Column(name="id")
    private String id;
    @Column(name="hospital_id")
    private String hospitalId;
    @Column(name="doctor_id")
    private String doctorId;
    @Column(name="speciality")
    private String speciality;
    @Column(name="degree")
    private String degree;

    public H2DMap(String hospitalId, String doctorId, String speciality, String degree) {
        this.hospitalId = hospitalId;
        this.doctorId = doctorId;
        this.speciality = speciality;
        this.degree = degree;
    }
}
