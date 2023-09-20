package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Doctor;
import com.bdehr.backend.Entity.User;
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
    public int addDoctor(@RequestParam Map<String, String> user){
        String name = user.get("name");
        String password = user.get("password");
        String email = user.get("email");
        String bmdc = user.get("bmdc");
        String dob = user.get("dob");
        String address = user.get("address");
        String gender = user.get("gender");
        String photo = user.get("photo");
        String phone = user.get("phone");

        Doctor tmp = null;
        tmp = doctorRepo.findByBmdc(bmdc);

        if(tmp!=null) return -1;

        tmp = doctorRepo.saveAndFlush(new Doctor(name, password, email, bmdc, dob, address, gender, photo, phone));
        System.out.println("Doctor Signup: "+tmp);
        return tmp.getId();
    }

    @PostMapping(path="doctor/login")
    public int loginDoctor(@RequestParam Map<String, String> doctor){
        int id = Integer.parseInt(doctor.get("id"));
        String password = doctor.get("password");

        Doctor tmp = null;
        tmp = doctorRepo.findByIdAndPassword(id, password);
        System.out.println("Doctor Login: "+tmp);
        if( tmp != null) return 1;
        else return 0;
    }

}
