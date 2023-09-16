package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.User;
import com.bdehr.backend.Interface.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @PostMapping(path="users/add")
    public void addUser(@RequestParam Map<String, String> user, HttpServletResponse response){
        String username = user.get("username");
        String password = user.get("password");
        String email = user.get("email");

        userRepo.save(new User(username, password, email));
        response.setStatus(201);
    }

    @CrossOrigin(origins = "*")
    @GetMapping(path="test")
    public String getHello(){
        return "Hello";
    }
}
