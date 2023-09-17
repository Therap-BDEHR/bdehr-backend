package com.bdehr.backend.Entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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


    public H2DMap(int hospital_id, int doctor_id) {
        this.hospital_id = hospital_id;
        this.doctor_id = doctor_id;
    }

}
