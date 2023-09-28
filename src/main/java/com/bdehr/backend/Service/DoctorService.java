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
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepo;

    @PostMapping(path="doctor/signup")
    public String addDoctor(@RequestParam Map<String, String> map){
        String name = map.get("name");
        String password = map.get("password");
        String email = map.get("email");
        String bmdc = map.get("bmdc");
        String dob = map.get("dob");
        String address = map.get("address");
        String gender = map.get("gender");
        String phone = map.get("phone");

        Doctor tmp = null;
        tmp = doctorRepo.findByBmdc(bmdc);

        if(tmp!=null) return "0";

        Doctor doctor = new Doctor(name, password, email, bmdc, dob, address, gender, phone);

        String customId;
        while(true) {
            UUID uuid = UUID.randomUUID();
            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
            if(doctorRepo.findById(customId) == null){
                break;
            }
        }

        doctor.setId(customId);

        doctorRepo.saveAndFlush(doctor);
        System.out.println("Doctor Signup: "+doctor);
        return doctor.getId();
    }

    @PostMapping(path="doctor/login")
    public Doctor loginDoctor(@RequestParam Map<String, String> doctor){
        String id = doctor.get("id");
        String password = doctor.get("password");

        Doctor tmp = null;
        tmp = doctorRepo.findByIdAndPassword(id, password);
        System.out.println("Doctor Login: "+tmp);
        return tmp;
    }

    @PostMapping(path="doctor/change-photo")
    public void changePhoto(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");
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
        String id = jo.getString("user_id");
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
