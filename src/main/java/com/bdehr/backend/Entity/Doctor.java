package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name="doctor_table")
public class Doctor{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="email")
    private String email;
    @Column(name="bmdc")
    private String bmdc;
    @Column(name="dob")
    private String dob;
    @Column(name="address")
    private String address;
    @Column(name="gender")
    private String gender;
    @Column(name="photo")
    private String photo;
    @Column(name="phone")
    private String phone;

    public Doctor(String name, String password, String email, String bmdc, String dob, String address, String gender, String photo, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.bmdc = bmdc;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.photo = photo;
        this.phone = phone;
    }

    public Doctor(String name, String password, String email, String bmdc, String dob, String address, String gender, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.bmdc = bmdc;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
    }
}
