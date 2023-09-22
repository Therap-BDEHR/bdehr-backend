package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.Hospital;
import com.bdehr.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    Hospital findByIdAndPassword (int id, String password);
    Hospital findById(int id);
    Hospital findByName(String name);
}
