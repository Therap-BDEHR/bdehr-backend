package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Hospital;
import com.bdehr.backend.Entity.User;
import com.bdehr.backend.Interface.HospitalRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepo;

    @PostMapping(path="hospital/signup")
    public void addHospital(@RequestParam Map<String, String> user, HttpServletResponse response){
        String name = user.get("name");
        String password = user.get("password");
        String dob = user.get("dob");
        String longitude = user.get("longitude");
        String latitude = user.get("latitude");
        String photo = user.get("photo");
        String phone = user.get("phone");
        String email = user.get("email");

        hospitalRepo.save(new Hospital(name, password, dob, longitude, latitude, photo, phone, email));
    }

    @PostMapping(path="hospital/login")
    public String loginHospital(@RequestParam Map<String, String> hospital){
        int id = Integer.parseInt(hospital.get("id"));
        String password = hospital.get("password");

        User tmp = null;
        tmp = hospitalRepo.findByIdAndPassword(id, password);
        if( tmp != null) return "true";
        else return "false";
    }

}
