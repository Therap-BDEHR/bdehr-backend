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

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class H2PMapService {
    @Autowired
    private H2PMapRepository h2pMapRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping(path="h2p/add-patient")
    private int addPatient(@RequestParam Map<String, String> map){
        int hospitalId = Integer.parseInt(map.get("hospitalId"));
        int doctorId = Integer.parseInt(map.get("doctorId"));
        int patientId = Integer.parseInt(map.get("patientId"));
        String patientName = map.get("patientName");
        String patientPhone = map.get("patientPhone");
        String status = map.get("status");

        h2pMapRepo.saveAndFlush(new H2PMap(hospitalId, patientId, doctorId, patientName, patientPhone, status));
//        System.out.println("H2P Add Patient: "+tmp);
        return 1;
    }

    @PostMapping(path="h2p/get-patient-list")
    private Pair<List<H2PMap>, List<User>> getPatientList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        int hospitalId = jo.getInt("hospitalId");

        List<H2PMap> h2pList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        h2pList = h2pMapRepo.findByHospitalId(hospitalId);

        for(H2PMap h2p : h2pList){
            User user = userRepo.findById(h2p.getPatientId());
            userList.add(user);
        }

        return Pair.of(h2pList, userList);
    }

    @GetMapping(path="h2p/test")
    private Pair<List<H2PMap>, List<User>> h2pTest(){
        List<H2PMap> h2pList = new ArrayList<>();
        List<User> userList = new ArrayList<>();

        h2pList.add(new H2PMap(1,1,11,"Pial","1111","Well"));
        h2pList.add(new H2PMap(2,2,22,"Sakib","2222","Sleeping"));
        h2pList.add(new H2PMap(3,3,33,"Salman","3333","Injured"));

        userList.add(new User(
           "Rakib",
           "12345",
                "rakib@gmail.com",
                "1905098",
                "10/04/2000",
                "Barisal",
                "Male",
                "67890"
        ));
        userList.add(new User(
                "Rifat",
                "12342",
                "rifat@gmail.com",
                "1905094",
                "13/07/2000",
                "Banani",
                "Male",
                "45624"
        ));
        userList.add(new User(
                "Nafi",
                "13454",
                "nafi@gmail.com",
                "1905100",
                "25/01/2000",
                "Jessore",
                "Male",
                "45486"
        ));

        return Pair.of(h2pList, userList);
    }
}
