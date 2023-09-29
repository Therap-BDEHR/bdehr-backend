package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.H2PMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface H2PMapRepository extends JpaRepository<H2PMap, Integer> {
    H2PMap findById(String id);
    void deleteById(String id);
    List<H2PMap> findByLabId(String labId);
    List<H2PMap> findByHospitalId(String hospitalId);
    List<H2PMap> findByPatientId(String patientId);
    List<H2PMap> findByDoctorIdAndHospitalId(String doctorId, String hospitalId);
}
