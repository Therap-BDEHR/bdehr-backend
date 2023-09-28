package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByIdAndPassword (String id, String password);
    User findByNid(String nid);
    User findById(String id);
}
