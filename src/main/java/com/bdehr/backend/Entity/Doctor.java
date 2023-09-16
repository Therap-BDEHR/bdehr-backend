package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

import java.util.Date;

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
    private Date dob;
    @Column(name="address")
    private String address;
    @Column(name="gender")
    private String gender;
    @Column(name="photo")
    private String photo;
    @Column(name="phone")
    private String phone;

    public Doctor() {
    }

    public Doctor(String name, String password, String email, String bmdc, Date dob, String address, String gender, String photo, String phone) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBmdc() {
        return bmdc;
    }

    public void setBmdc(String bmdc) {
        this.bmdc = bmdc;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
