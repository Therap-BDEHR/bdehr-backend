package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
@Table(name="hospital_table")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="dob")
    private String dob;
    @Column(name="longitude")
    private String longitude;
    @Column(name="latitude")
    private String latitude;
    @Column(name="photo")
    private String photo;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;

    public Hospital(String name, String password, String dob, String longitude, String latitude, String photo, String phone,String email) {
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.longitude = longitude;
        this.latitude = latitude;
        this.photo = photo;
        this.phone = phone;
        this.email = email;
    }

}
