package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Integer> {
    Medication findById(String id);
    List<Medication> findByIsDone(String isDone);
    Medication findByPatientIdAndDoctorIdAndHospitalIdAndIsDone(String patientId, String doctorId, String hospitalId, String isDone);
    List<Medication> findByPatientIdAndIsDone(String patientId, String isDone);
}
