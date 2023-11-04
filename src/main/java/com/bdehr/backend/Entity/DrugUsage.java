package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "drug_usage_table")
public class DrugUsage {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="variant")
    private String variant;
    @Column(name="generic")
    private String generic;
    @Column(name="date")
    private String date;
    @Column(name="patient_id")
    private String patientId;
    @Column(name="company")
    private String company;
    @Column(name="hospital")
    private String hospital;
    @Column(name="category")
    private String category;

    public DrugUsage(String name, String variant, String generic, String date, String patientId, String company, String hospital, String category) {
        this.name = name;
        this.variant = variant;
        this.generic = generic;
        this.date = date;
        this.patientId = patientId;
        this.company = company;
        this.hospital = hospital;
        this.category = category;
    }
}
