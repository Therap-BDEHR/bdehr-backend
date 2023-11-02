package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Company findByIdAndPassword (String id, String password);
    Company findByName(String name);
    Company findById(String id);
}
