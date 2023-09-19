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
    private int hospitalId;
    @Column(name="patient_id")
    private int patientId;
    @Column(name="doctor_id")
    private int doctorId;
    @Column(name="status")
    private String status;
    @Column(name="admit_date",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date admin_date;

    public H2PMap(int hospitalId, int patientId,int doctorId, String status) {
        this.hospitalId = hospitalId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.status = status;
    }
}
