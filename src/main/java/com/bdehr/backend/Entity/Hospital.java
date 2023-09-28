package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="hospital_table")
public class Hospital {
    @Id
    @Column(name="id")
    private String id;
    @Column(name="name")
    private String name;
    @Column(name="password")
    private String password;
    @Column(name="auth_password")
    private String authPassword;
    @Column(name="dob")
    private String dob;
    @Column(name="address")
    private String address;
    @Column(name="longitude")
    private String longitude;
    @Column(name="latitude")
    private String latitude;
    @Column(name="photo")
    private String photo;
    @Column(name="logo")
    private String logo;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name="num_dept")
    private int numDept;
    @Column(name="dept_list")
    private String deptList;
    @Column(name="tool_list")
    private String toolList;

    public Hospital(String name, String password, String authPassword, String dob, String address, String longitude, String latitude, String photo, String logo, String phone, String email, int numDept, String deptList, String toolList) {
        this.name = name;
        this.password = password;
        this.authPassword = authPassword;
        this.dob = dob;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.photo = photo;
        this.logo = logo;
        this.phone = phone;
        this.email = email;
        this.numDept = numDept;
        this.deptList = deptList;
        this.toolList = toolList;
    }

    public Hospital(String id, String name, String password, String authPassword, String dob, String address, String longitude, String latitude, String photo, String logo, String phone, String email, int numDept, String deptList, String toolList) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.authPassword = authPassword;
        this.dob = dob;
        this.address = address;
        this.longitude = longitude;
        this.latitude = latitude;
        this.photo = photo;
        this.logo = logo;
        this.phone = phone;
        this.email = email;
        this.numDept = numDept;
        this.deptList = deptList;
        this.toolList = toolList;
    }
}
