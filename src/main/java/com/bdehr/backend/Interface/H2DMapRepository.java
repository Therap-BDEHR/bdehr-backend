package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.H2DMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface H2DMapRepository extends JpaRepository<H2DMap, Integer> {
    H2DMap findById(int id);
    List<H2DMap> findByDoctorId(int doctorId);
}
