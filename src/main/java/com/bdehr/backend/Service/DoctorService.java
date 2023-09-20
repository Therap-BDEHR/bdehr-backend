package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Doctor;
import com.bdehr.backend.Entity.User;
import com.bdehr.backend.Interface.DoctorRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.Map;
import java.util.Optional;

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
//        String photo = user.get("photo");
        String phone = user.get("phone");

        Doctor tmp = null;
        tmp = doctorRepo.findByBmdc(bmdc);

        if(tmp!=null) return 0;

        tmp = doctorRepo.saveAndFlush(new Doctor(name, password, email, bmdc, dob, address, gender, phone));
        System.out.println("Doctor Signup: "+tmp);
        return tmp.getId();
    }

    @PostMapping(path="doctor/login")
    public Doctor loginDoctor(@RequestParam Map<String, String> doctor){
        int id = Integer.parseInt(doctor.get("id"));
        String password = doctor.get("password");

        Doctor tmp = null;
        tmp = doctorRepo.findByIdAndPassword(id, password);
        System.out.println("Doctor Login: "+tmp);
        return tmp;
    }

    @PostMapping(path="doctor/change-photo")
    public void changePhoto(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        int id = jo.getInt("id");
        String url = jo.getString("url");

        Optional<Doctor> tmp = Optional.ofNullable(doctorRepo.findById(id));
        if(tmp.isPresent()){
            tmp.get().setPhoto(url);
            doctorRepo.saveAndFlush(tmp.get());
        }
    }

    @PostMapping(path="doctor/update-doctor")
    public void updateDoctor(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        System.out.println(jo);
        int id = jo.getInt("doctor_id");
        String name = jo.getString("name");
        String email = jo.getString("email");
        String bmdc = jo.getString("bmdc");
        String dob = jo.getString("dob");
        String address = jo.getString("address");
        String gender = jo.getString("gender");
        String photo = jo.getString("doctorImage");
        String phone = jo.getString("phone");

        Optional<Doctor> tmp = Optional.ofNullable(doctorRepo.findById(id));
        if(tmp.isPresent()){
            tmp.get().setEmail(email);
            tmp.get().setBmdc(bmdc);
            tmp.get().setDob(dob);
            tmp.get().setAddress(address);
            tmp.get().setGender(gender);
            tmp.get().setPhoto(photo);
            tmp.get().setPhone(phone);
            tmp.get().setName(name);
            doctorRepo.saveAndFlush(tmp.get());
        }
    }

}
