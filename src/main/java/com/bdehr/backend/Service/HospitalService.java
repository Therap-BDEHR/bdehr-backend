package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Hospital;
import com.bdehr.backend.Interface.HospitalRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepo;

    @PostMapping(path="hospital/signup")
    public int addHospital(@RequestParam Map<String, String> user, HttpServletResponse response){
        String name = user.get("name");
        String password = user.get("password");
        String apassword = user.get("apassword");
        String dob = user.get("dob");
        String longitude = user.get("longitude");
        String latitude = user.get("latitude");
        String photo = user.get("photo");
        String phone = user.get("phone");
        String email = user.get("email");

        Hospital tmp = null;
        tmp = hospitalRepo.findByName(name);

        if(tmp!=null) return 0;

        tmp = hospitalRepo.saveAndFlush(new Hospital(name, password, apassword, dob, longitude, latitude, photo, phone, email));
        System.out.println("Hospital Signup: "+tmp);
        return tmp.getId();
    }

    @PostMapping(path="hospital/login")
    public Hospital loginHospital(@RequestParam Map<String, String> hospital){
        int id = Integer.parseInt(hospital.get("id"));
        String password = hospital.get("password");

        Hospital tmp = null;
        tmp = hospitalRepo.findByIdAndPassword(id, password);
        System.out.println("Hospital Login: "+tmp);
        return tmp;
    }

    @PostMapping(path="hospital/change-photo")
    public void changePhoto(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        int id = jo.getInt("id");
        String url = jo.getString("url");

        Optional<Hospital> tmp = Optional.ofNullable(hospitalRepo.findById(id));
        if(tmp.isPresent()){
            tmp.get().setPhoto(url);
            hospitalRepo.saveAndFlush(tmp.get());
        }
    }

    @PostMapping(path="hospital/update-hospital")
    public void updateDoctor(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        System.out.println(jo);
        int id = jo.getInt("hospital_id");
        String name = jo.getString("name");
        String email = jo.getString("email");
        String dob = jo.getString("dob");
        String longitude = jo.getString("longitude");
        String latitude = jo.getString("latitude");
        String photo = jo.getString("hospitalImage");
        String phone = jo.getString("phone");

        Optional<Hospital> tmp = Optional.ofNullable(hospitalRepo.findById(id));
        if(tmp.isPresent()){
            tmp.get().setEmail(email);
            tmp.get().setLatitude(latitude);
            tmp.get().setLongitude(longitude);
            tmp.get().setDob(dob);
            tmp.get().setPhoto(photo);
            tmp.get().setPhone(phone);
            tmp.get().setName(name);
            hospitalRepo.saveAndFlush(tmp.get());
        }
    }

}
