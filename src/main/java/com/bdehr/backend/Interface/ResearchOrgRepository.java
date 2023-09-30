package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.ResearchOrg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResearchOrgRepository extends JpaRepository<ResearchOrg, Integer> {
    ResearchOrg findById(String id);
    ResearchOrg findByName(String name);
    ResearchOrg findByIdAndPassword(String id, String password);
}
