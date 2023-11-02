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
    @Column(name="user_id")
    private String userId;
    @Column(name="company")
    private String company;
    @Column(name="hospital")
    private String hospital;
    @Column(name="latitude")
    private String latitude;
    @Column(name="longitude")
    private String longitude;

    public DrugUsage(String name, String variant, String generic, String date, String userId, String company, String hospital, String latitude, String longitude) {
        this.name = name;
        this.variant = variant;
        this.generic = generic;
        this.date = date;
        this.userId = userId;
        this.company = company;
        this.hospital = hospital;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
