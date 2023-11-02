package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.User;
import com.bdehr.backend.Interface.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class UserService {
    @Autowired
    private UserRepository userRepo;

    @PostMapping(path="user/signup")
    public String addUser(@RequestParam Map<String, String> map){
        String name = map.get("name");
        String password = map.get("password");
        String email = map.get("email");
        String nid = map.get("nid");
        String dob = map.get("dob");
        String address = map.get("address");
        String gender = map.get("gender");
        String phone = map.get("phone");

        User tmp = null;
        tmp = userRepo.findByNid(nid);

        if(tmp!=null) return "0";

        User user = new User(name, password, email, nid, dob, address, gender, phone);

        String customId;
        while(true) {
            UUID uuid = UUID.randomUUID();
            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
            if(userRepo.findById(customId) == null){
                break;
            }
        }

        user.setId(customId);

        userRepo.saveAndFlush(user);
        System.out.println("User Signup: "+user);
        return user.getId();
    }

    @PostMapping(path="user/login")
    public User loginUser(@RequestParam Map<String, String> user){
        String id = user.get("id");
        String password = user.get("password");

        User tmp = null;
        tmp = userRepo.findByIdAndPassword(id, password);
        System.out.println("User Login: "+tmp);
        return tmp;
    }

    @PostMapping(path="user/change-photo")
    public void changePhoto(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");
        String url = jo.getString("url");

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
        String id = jo.getString("user_id");
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
