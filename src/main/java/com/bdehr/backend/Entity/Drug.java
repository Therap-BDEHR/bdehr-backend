package com.bdehr.backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "drug_table")
public class Drug{
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="photo")
    private String photo;
    @Column(name="variant")
    private String variant;
    @Column(name="chemicalName")
    private String chemicalName;
    @Column(name="intensity")
    private String intensity;
    @Column(name="category")
    private String category;
    @Column(name="best_before")
    private String bestBefore;
    @Column(name="indications")
    private String indications;
    @Column(name="pharmacology")
    private String pharmacology;
    @Column(name="dosage")
    private String dosage;
    @Column(name="administration")
    private String administration;
    @Column(name="interaction")
    private String interaction;
    @Column(name="side_effects")
    private String sideEffects;
    @Column(name="precautions")
    private String precautions;
    @Column(name="storage_conditions")
    private String storageConditions;
    @Column(name="unit_sold")
    private int unitSold;
    @Column(name="company")
    private String company;

    public Drug(String name, String variant, String chemicalName, String intensity, String category, String bestBefore, String indications, String pharmacology, String dosage, String administration, String interaction, String sideEffects, String precautions, String storageConditions, int unitSold, String company) {
        this.name = name;
        this.variant = variant;
        this.chemicalName = chemicalName;
        this.intensity = intensity;
        this.category = category;
        this.bestBefore = bestBefore;
        this.indications = indications;
        this.pharmacology = pharmacology;
        this.dosage = dosage;
        this.administration = administration;
        this.interaction = interaction;
        this.sideEffects = sideEffects;
        this.precautions = precautions;
        this.storageConditions = storageConditions;
        this.unitSold = unitSold;
        this.company = company;
    }
}
