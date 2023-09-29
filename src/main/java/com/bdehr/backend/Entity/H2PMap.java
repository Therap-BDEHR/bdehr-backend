package com.bdehr.backend.Entity;

import com.bdehr.backend.Interface.H2PMapRepository;
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
    @Column(name="id")
    private String id;
    @Column(name="hospital_id")
    private String hospitalId;
    @Column(name="patient_id")
    private String patientId;
    @Column(name="doctor_id")
    private String doctorId;
    @Column(name="lab_id")
    private String labId;
    @Column(name="patient_name")
    private String patientName;
    @Column(name="patient_phone")
    private String patientPhone;
    @Column(name="status")
    private String status;
    @Column(name="admit_date")
    private String admitDate;

    public H2PMap(String hospitalId, String patientId, String doctorId, String labId, String patientName, String patientPhone, String status) {
        this.hospitalId = hospitalId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.labId = labId;
        this.patientName = patientName;
        this.patientPhone = patientPhone;
        this.status = status;
    }

    public H2PMap(String hospitalId, String patientId, String doctorId, String labId, String patientName, String patientPhone, String status, String admitDate) {
        this.hospitalId = hospitalId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.labId = labId;
        this.patientName = patientName;
        this.patientPhone = patientPhone;
        this.status = status;
        this.admitDate = admitDate;
    }

    public H2PMap(String hospitalId, String patientId, String patientName, String patientPhone, String status, String admitDate) {
        this.hospitalId = hospitalId;
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientPhone = patientPhone;
        this.status = status;
        this.admitDate = admitDate;
    }
}
