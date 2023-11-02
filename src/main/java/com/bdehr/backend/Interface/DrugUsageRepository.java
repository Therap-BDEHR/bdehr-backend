package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.DrugUsage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugUsageRepository extends JpaRepository<DrugUsage, Integer> {
    List<DrugUsage> findByCompany(String company);
}
