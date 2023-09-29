package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Integer> {
    Machine findById(int id);
    List<Machine>findByLabId(String labId);
}
