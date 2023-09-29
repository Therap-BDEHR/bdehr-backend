package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.HospitalRequest;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRequestRepository extends JpaRepository<HospitalRequest,Integer> {
    HospitalRequest findById(String id);
    void deleteById(String id);
    HospitalRequest findByName(String name);
}
