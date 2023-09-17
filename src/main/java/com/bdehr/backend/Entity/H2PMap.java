package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
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

    public H2PMap(int hospital_id, int patient_id, String status) {
        this.hospital_id = hospital_id;
        this.patient_id = patient_id;
        this.status = status;
    }
}
