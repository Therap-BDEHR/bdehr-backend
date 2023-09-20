package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class User {
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
//    @Column(name="created_on",columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
//    private Date created_on;
    @Column(name="nid")
    private String nid;
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

    public User(String name, String password, String email, String nid, String dob, String address, String gender, String photo, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.nid = nid;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.photo = photo;
        this.phone = phone;
    }

    public User(String name, String password, String email, String nid, String dob, String address, String gender, String phone) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.nid = nid;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.phone = phone;
    }

}
