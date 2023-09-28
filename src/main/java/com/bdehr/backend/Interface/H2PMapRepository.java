package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.H2PMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface H2PMapRepository extends JpaRepository<H2PMap, Integer> {
    H2PMap findById(String id);
    List<H2PMap> findByDoctorId(String doctorId);
    List<H2PMap> findByHospitalId(String hospitalId);
}
