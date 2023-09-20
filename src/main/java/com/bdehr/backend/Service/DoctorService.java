package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Doctor;
import com.bdehr.backend.Interface.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepo;

    @PostMapping(path="doctor/signup")
    public void addDoctor(@RequestParam Map<String, String> user){
        String name = user.get("name");
        String password = user.get("password");
        String email = user.get("email");
        String bmdc = user.get("dmdc");
        String dob = user.get("dob");
        String address = user.get("address");
        String gender = user.get("gender");
        String photo = user.get("photo");
        String phone = user.get("phone");

        doctorRepo.save(new Doctor(name, password, email, bmdc, dob, address, gender, photo, phone));
    }

    @PostMapping(path="doctor/login")
    public String loginDoctor(@RequestParam Map<String, String> doctor){
        int id = Integer.parseInt(doctor.get("id"));
        String password = doctor.get("password");

        Doctor tmp = null;
        tmp = doctorRepo.findByIdAndPassword(id, password);
        if( tmp != null) return "true";
        else return "false";
    }

}
