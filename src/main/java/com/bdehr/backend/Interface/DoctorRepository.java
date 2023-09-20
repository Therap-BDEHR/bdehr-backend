package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByIdAndPassword(int id, String password);
    Doctor findByBmdc(String bmdc);
}
