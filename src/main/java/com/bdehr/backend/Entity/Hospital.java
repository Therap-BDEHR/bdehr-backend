package com.bdehr.backend.Entity;

import jakarta.persistence.*;

import java.util.Date;

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

    public Hospital() {
    }

    public Hospital(String name, String password, Date dob, String address, String photo, String email) {
        this.name = name;
        this.password = password;
        this.dob = dob;
        this.address = address;
        this.photo = photo;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
