package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Hospital;
import com.bdehr.backend.Entity.Machine;
import com.bdehr.backend.Interface.HospitalRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @PostMapping(path="hospital/get-hospital-info")
    public Hospital getHospitalInfo(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String hospitalId = jo.getString("hospitalId");

        return hospitalRepo.findById(hospitalId);
    }

    @GetMapping(path="hospital/get-all-location")
    public List<String> getAllLocation(){
        List<Hospital> hospitalList = hospitalRepo.findAll();
        List<String> stringList = new ArrayList<>();

        for(Hospital hospital: hospitalList){
            JSONObject tmp = new JSONObject();

            tmp.put("latitude",hospital.getLatitude());
            tmp.put("longitude",hospital.getLongitude());
            tmp.put("name",hospital.getName());

            stringList.add(tmp.toString());
        }

        return stringList;
    }
}
