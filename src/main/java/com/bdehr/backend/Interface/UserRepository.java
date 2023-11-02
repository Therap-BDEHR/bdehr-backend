package com.bdehr.backend.Interface;

import com.bdehr.backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByIdAndPassword (String id, String password);
    User findByNid(String nid);
    User findById(String id);
}
