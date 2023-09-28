package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.H2PMap;
import com.bdehr.backend.Entity.User;
import com.bdehr.backend.Interface.H2PMapRepository;
import com.bdehr.backend.Interface.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class H2PMapService {
    @Autowired
    private H2PMapRepository h2pMapRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping(path="h2p/add-patient")
    private int addPatient(@RequestParam Map<String, String> map){
        String hospitalId = map.get("hospitalId");
        String doctorId = map.get("doctorId");
        String patientId = map.get("patientId");
        String patientName = map.get("patientName");
        String patientPhone = map.get("patientPhone");
        String status = map.get("status");

        H2PMap h2p = new H2PMap(hospitalId, patientId, doctorId, patientName, patientPhone, status);

        String customId;
        while(true) {
            UUID uuid = UUID.randomUUID();
            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
            if(h2pMapRepo.findById(customId) == null){
                break;
            }
        }

        h2p.setId(customId);


        h2pMapRepo.saveAndFlush(h2p);
        System.out.println("H2P Add Patient: "+h2p);
        return 1;
    }

    @PostMapping(path="h2p/get-patient-list")
    private List<String> getPatientList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String hospitalId = jo.getString("hospitalId");

        List<H2PMap> h2pList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        h2pList = h2pMapRepo.findByHospitalId(hospitalId);

        List<String> stringList = new ArrayList<>();

        for(H2PMap h2p : h2pList){
            User user = userRepo.findById(h2p.getPatientId());
            userList.add(user);

            JSONObject tmp = new JSONObject();

            tmp.put("patientName",h2p.getPatientName());
            tmp.put("patientId",h2p.getPatientId());
            tmp.put("hospitalId",h2p.getHospitalId());
            tmp.put("doctorId",h2p.getDoctorId());
            tmp.put("patientPhone",h2p.getPatientPhone());
            tmp.put("status",h2p.getStatus());
            tmp.put("admitDate",h2p.getAdmitDate());

            tmp.put("dob",user.getDob());
            tmp.put("photo",user.getPhoto());

            stringList.add(tmp.toString());
        }

        return stringList;
    }
//
//    @GetMapping(path="h2p/test")
//    private List<String> h2pTest(){
//        List<H2PMap> h2pList = new ArrayList<>();
//        List<User> userList = new ArrayList<>();
//
//        List<String> stringList = new ArrayList<>();
//
//        JSONObject tmp1 = new JSONObject();
//        tmp1.put("name","Pial");
//        tmp1.put("roll","1905112");
//        stringList.add(tmp1.toString());
//
//        JSONObject tmp2 = new JSONObject();
//        tmp2.put("name","Pial");
//        tmp2.put("roll","1905098");
//        stringList.add(tmp2.toString());
//
//        JSONObject tmp3 = new JSONObject();
//        tmp3.put("name","Sakib");
//        tmp3.put("roll","1905061");
//        stringList.add(tmp3.toString());
//
//        return stringList;
//    }
}
