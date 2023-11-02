package com.bdehr.backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "company_table")
public class Company {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="logo")
    private String logo;
    @Column(name="factory_address")
    private String factoryAddress;
    @Column(name="factory_about")
    private String factoryAbout;
    @Column(name="factory_image")
    private String factoryImage;
    @Column(name="latitude")
    private String latitude;
    @Column(name="longitude")
    private String longitude;

    public Company(String name, String password, String email, String factoryAddress, String factoryAbout, String latitude, String longitude) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.factoryAddress = factoryAddress;
        this.factoryAbout = factoryAbout;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
