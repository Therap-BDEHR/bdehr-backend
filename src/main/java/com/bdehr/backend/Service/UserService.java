package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.User;
import com.bdehr.backend.Interface.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
    public int addUser(@RequestParam Map<String, String> user){
        String name = user.get("name");
        String password = user.get("password");
        String email = user.get("email");
        String nid = user.get("nid");
        String dob = user.get("dob");
        String address = user.get("address");
        String gender = user.get("gender");
        //String photo = user.get("photo");
        String phone = user.get("phone");

        User tmp = null;
        tmp = userRepo.findByNid(nid);

        if(tmp!=null) return 0;

//        tmp = userRepo.saveAndFlush(new User(name, password, email, nid, dob, address, gender, photo, phone));
        tmp = userRepo.saveAndFlush(new User(name, password, email, nid, dob, address, gender, phone));
        System.out.println("User Signup: "+tmp);
        return tmp.getId();
    }

    @PostMapping(path="user/login")
    public User loginUser(@RequestParam Map<String, String> user){
        int id = Integer.parseInt(user.get("id"));
        String password = user.get("password");

        User tmp = null;
        tmp = userRepo.findByIdAndPassword(id, password);
        System.out.println("User Login: "+tmp);
        return tmp;
    }

    @PostMapping(path="user/change-photo")
    public void changePhoto(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        int id = jo.getInt("id");
        String url = jo.getString("url");

        System.out.println(id + " " + url);

        Optional<User> tmp = Optional.ofNullable(userRepo.findById(id));
        if(tmp.isPresent()){
            tmp.get().setPhoto(url);
            userRepo.saveAndFlush(tmp.get());
        }
    }

    @PostMapping(path="user/update-user")
    public void updateUser(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        System.out.println(jo);
        int id = jo.getInt("user_id");
        String name = jo.getString("name");
        String email = jo.getString("email");
        String nid = jo.getString("nid");
        String dob = jo.getString("dob");
        String address = jo.getString("address");
        String gender = jo.getString("gender");
        String photo = jo.getString("userImage");
        String phone = jo.getString("phone");

        Optional<User> tmp = Optional.ofNullable(userRepo.findById(id));
        if(tmp.isPresent()){
            tmp.get().setEmail(email);
            tmp.get().setNid(nid);
            tmp.get().setDob(dob);
            tmp.get().setAddress(address);
            tmp.get().setGender(gender);
            tmp.get().setPhoto(photo);
            tmp.get().setPhone(phone);
            tmp.get().setName(name);
            userRepo.saveAndFlush(tmp.get());
        }
    }

//    @PostMapping(path="test")
//    public void getHello(@RequestParam Map<String, String> map){
//        System.out.println(map);
//    }
}
