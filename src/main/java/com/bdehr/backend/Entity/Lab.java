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
@Table(name="lab_table")
public class Lab {
    @Id
    @Column(name="id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="hospital_id")
    private String hospitalId;

    @Column(name="password")
    private String password;

    public Lab(String name, String hospitalId, String password) {
        this.name = name;
        this.hospitalId = hospitalId;
        this.password = password;
    }
}
