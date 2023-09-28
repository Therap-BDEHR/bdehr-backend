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
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class HospitalService {
    @Autowired
    private HospitalRepository hospitalRepo;

//    @PostMapping(path="hospital/signup")
//    public String addHospital(@RequestParam Map<String, String> map){
//        String name = map.get("name");
//        String password = map.get("password");
//        String authPassword = map.get("authPassword");
//        String dob = map.get("dob");
//        String address = map.get("address");
//        String longitude = map.get("longitude");
//        String latitude = map.get("latitude");
//        String photo = map.get("photo");
//        String logo = map.get("logo");
//        String phone = map.get("phone");
//        String email = map.get("email");
//        int numDept = Integer.parseInt(map.get("numDept"));
//        String deptList = map.get("deptList");
//        String toolList = map.get("toolList");
//
//        Hospital tmp = null;
//        tmp = hospitalRepo.findByName(name);
//
//        if(tmp!=null) return "0";
//
//        Hospital hospital = new Hospital(name, password, authPassword, dob, address, longitude, latitude, photo, logo, phone, email, numDept, deptList, toolList);
//
//        String customId;
//        while(true) {
//            UUID uuid = UUID.randomUUID();
//            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
//            if(hospitalRepo.findById(customId) == null){
//                break;
//            }
//        }
//
//        hospital.setId(customId);
//
//        hospitalRepo.saveAndFlush(hospital);
//        System.out.println("Hospital Signup: "+hospital);
//        return hospital.getId();
//    }

    @PostMapping(path="hospital/login")
    public Hospital loginHospital(@RequestParam Map<String, String> hospital){
        String id = hospital.get("id");
        String password = hospital.get("password");

        Hospital tmp = null;
        tmp = hospitalRepo.findByIdAndPassword(id, password);
        System.out.println("Hospital Login: "+tmp);
        return tmp;
    }

    @PostMapping(path="hospital/auth-login")
    public Hospital loginAuthHospital(@RequestParam Map<String, String> hospital){
        String id = hospital.get("id");
        String authPassword = hospital.get("authPassword");

        Hospital tmp = null;
        tmp = hospitalRepo.findByIdAndAuthPassword(id, authPassword);
        System.out.println("Hospital Auth Login: "+tmp);
        return tmp;
    }

//    @PostMapping(path="hospital/change-photo")
//    public void changePhoto(HttpEntity<String> httpEntity){
//        JSONObject jo = new JSONObject(httpEntity.getBody());
//        String id = jo.getString("id");
//        String url = jo.getString("url");
//
//        Optional<Hospital> tmp = Optional.ofNullable(hospitalRepo.findById(id));
//        if(tmp.isPresent()){
//            tmp.get().setPhoto(url);
//            hospitalRepo.saveAndFlush(tmp.get());
//        }
//    }

//    @PostMapping(path="hospital/update-hospital")
//    public void updateDoctor(HttpEntity<String> httpEntity){
//        JSONObject jo = new JSONObject(httpEntity.getBody());
//        System.out.println(jo);
//        String id = jo.getString("hospital_id");
//        String name = jo.getString("name");
//        String email = jo.getString("email");
//        String dob = jo.getString("dob");
//        String longitude = jo.getString("longitude");
//        String latitude = jo.getString("latitude");
//        String photo = jo.getString("hospitalImage");
//        String phone = jo.getString("phone");
//
//        Optional<Hospital> tmp = Optional.ofNullable(hospitalRepo.findById(id));
//        if(tmp.isPresent()){
//            tmp.get().setEmail(email);
//            tmp.get().setLatitude(latitude);
//            tmp.get().setLongitude(longitude);
//            tmp.get().setDob(dob);
//            tmp.get().setPhoto(photo);
//            tmp.get().setPhone(phone);
//            tmp.get().setName(name);
//            hospitalRepo.saveAndFlush(tmp.get());
//        }
//    }

}
