package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.H2DMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface H2DMapRepository extends JpaRepository<H2DMap, Integer> {
    List<H2DMap> findByHospitalId(int hospitalId);
    List<H2DMap> findByDoctorId(int doctorId);
}
