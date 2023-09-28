package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabRepository extends JpaRepository<Lab, Integer> {
    Lab findById(String id);
    Lab findByIdAndHospitalIdAndPassword(String id, String hospitalId, String password);
    List<Lab> findByHospitalId(String hospitalId);
}
