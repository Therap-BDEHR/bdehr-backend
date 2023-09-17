package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private Date dob;
    @Column(name="address")
    private String address;
    @Column(name="photo")
    private String photo;
    @Column(name="email")
    private String email;

    public Hospital(String name, String password, Date dob, String address, String photo, String email) {
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.address = address;
        this.photo = photo;
        this.email = email;
    }
}
