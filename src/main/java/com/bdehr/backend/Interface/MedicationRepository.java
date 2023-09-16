package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
}
