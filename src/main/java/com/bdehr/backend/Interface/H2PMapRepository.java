package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.H2PMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface H2PMapRepository extends JpaRepository<H2PMap, Integer> {
    H2PMap findById(int id);
    List<H2PMap> findByDoctorId(int doctorId);
    List<H2PMap> findByHospitalId(int hospitalId);
}
