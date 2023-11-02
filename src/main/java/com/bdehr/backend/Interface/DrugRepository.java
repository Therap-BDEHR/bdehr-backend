package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug,Integer> {
    Drug findById(int id);
    Drug findByName(String name);
    void deleteByName(String name);
    List<Drug> findByCompany(String company);
}
