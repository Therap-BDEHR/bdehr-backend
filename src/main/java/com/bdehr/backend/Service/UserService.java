package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.User;
import com.bdehr.backend.Interface.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @PostMapping(path="user/signup")
    public void addUser(@RequestParam Map<String, String> user, HttpServletResponse response){
        String name = user.get("name");
        String password = user.get("password");
        String email = user.get("email");
        String nid = user.get("nid");
        String dob = user.get("dob");
        String address = user.get("address");
        String gender = user.get("gender");
        String photo = user.get("photo");
        String phone = user.get("phone");

        userRepo.save(new User(name, password, email, nid, dob, address, gender, photo, phone));
    }

    @PostMapping(path="user/login")
    public String loginUser(@RequestParam Map<String, String> user){
        int id = Integer.parseInt(user.get("id"));
        String password = user.get("password");

        User tmp = null;
        tmp = userRepo.findByIdAndPassword(id, password);
        if( tmp != null) return "true";
        else return "false";
    }

    @PostMapping(path="test")
    public void getHello(@RequestParam Map<String, String> map){
        System.out.println(map);
    }
}
