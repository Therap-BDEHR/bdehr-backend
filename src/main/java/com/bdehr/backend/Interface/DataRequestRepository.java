package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.DataRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataRequestRepository extends JpaRepository<DataRequest, Integer> {
    List<DataRequest> findByStatus(String Status);
    DataRequest findById(int id);
    List<DataRequest> findBySender(String sender);
}
