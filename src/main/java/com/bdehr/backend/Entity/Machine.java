package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="machine_table")
public class Machine{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="lab_id")
    private String labId;

    @Column(name="model")
    private String model;

    @Column(name="company")
    private String company;

    @Column(name="i_date")
    private String iDate;

    @Column(name="photo")
    private String photo;

    public Machine(String name, String labId, String model, String company, String iDate, String photo) {
        this.name = name;
        this.labId = labId;
        this.model = model;
        this.company = company;
        this.iDate = iDate;
        this.photo = photo;
    }
}
