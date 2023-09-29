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
    private String addPatient(@RequestParam Map<String, String> map){
        String hospitalId = map.get("hospitalId");
        String doctorId = map.get("doctorId");
        String labId = map.get("labId");
        String patientId = map.get("patientId");
        String patientName = map.get("patientName");
        String patientPhone = map.get("patientPhone");
        String status = map.get("status");

        User tmp = null;
        tmp = userRepo.findById(patientId);
        if(tmp==null) return "0";

        H2PMap h2p = new H2PMap(hospitalId, patientId, doctorId, labId, patientName, patientPhone, status, java.time.LocalDate.now().toString());

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
        return "1";
    }

    @PostMapping(path="h2p/get-hospital-patient-list")
    private List<String> getHospitalPatientList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String hospitalId = jo.getString("hospitalId");

        List<H2PMap> h2pList = new ArrayList<>();

        h2pList = h2pMapRepo.findByHospitalId(hospitalId);

        List<String> stringList = new ArrayList<>();

        for(H2PMap h2p : h2pList){
            User user = userRepo.findById(h2p.getPatientId());

            JSONObject tmp = new JSONObject();

            tmp.put("id",h2p.getId());
            tmp.put("patientName",h2p.getPatientName());
            tmp.put("patientId",h2p.getPatientId());
            tmp.put("hospitalId",h2p.getHospitalId());
            tmp.put("doctorId",h2p.getDoctorId());
            tmp.put("labId",h2p.getLabId());
            tmp.put("patientPhone",h2p.getPatientPhone());
            tmp.put("status",h2p.getStatus());
            tmp.put("admitDate",h2p.getAdmitDate());

            tmp.put("dob",user.getDob());
            tmp.put("photo",user.getPhoto());

            stringList.add(tmp.toString());
        }

        return stringList;
    }

    @PostMapping(path="h2p/get-hospital-patient-cnt")
    private int getHospitalPatientCnt(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String hospitalId = jo.getString("hospitalId");

        List<H2PMap> h2pList = h2pMapRepo.findByHospitalId(hospitalId);
//        System.out.println("Patient Cnt: "+h2pList.size());
        return h2pList.size();
    }

    @PostMapping(path="h2p/get-doctor-patient-list")
    private List<String> getDoctorPatientList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());

        String hospitalId = jo.getString("hospitalId");
        String doctorId = jo.getString("doctorId");

        List<H2PMap> h2pList = new ArrayList<>();

        h2pList = h2pMapRepo.findByDoctorIdAndHospitalId(doctorId, hospitalId);

        List<String> stringList = new ArrayList<>();

        for(H2PMap h2p : h2pList){
            User user = userRepo.findById(h2p.getPatientId());

            JSONObject tmp = new JSONObject();

            tmp.put("id",h2p.getId());
            tmp.put("patientName",h2p.getPatientName());
            tmp.put("patientId",h2p.getPatientId());
            tmp.put("hospitalId",h2p.getHospitalId());
            tmp.put("doctorId",h2p.getDoctorId());
            tmp.put("labId",h2p.getLabId());
            tmp.put("patientPhone",h2p.getPatientPhone());
            tmp.put("status",h2p.getStatus());
            tmp.put("admitDate",h2p.getAdmitDate());

            tmp.put("dob",user.getDob());
            tmp.put("photo",user.getPhoto());

            stringList.add(tmp.toString());
        }
//        System.out.println(stringList);
        return stringList;
    }

    @PostMapping(path="h2p/get-lab-patient-list")
    private List<String> getLabPatientList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());

        String labId = jo.getString("labId");

        List<H2PMap> h2pList = new ArrayList<>();

        h2pList = h2pMapRepo.findByLabId(labId);

        List<String> stringList = new ArrayList<>();

        for(H2PMap h2p : h2pList){
            User user = userRepo.findById(h2p.getPatientId());

            JSONObject tmp = new JSONObject();

            tmp.put("id",h2p.getId());
            tmp.put("patientName",h2p.getPatientName());
            tmp.put("patientId",h2p.getPatientId());
            tmp.put("hospitalId",h2p.getHospitalId());
            tmp.put("doctorId",h2p.getDoctorId());
            tmp.put("labId",h2p.getLabId());
            tmp.put("patientPhone",h2p.getPatientPhone());
            tmp.put("status",h2p.getStatus());
            tmp.put("admitDate",h2p.getAdmitDate());

            tmp.put("dob",user.getDob());
            tmp.put("photo",user.getPhoto());

            stringList.add(tmp.toString());
        }

        return stringList;
    }

    @PostMapping(path="h2p/get-patient-data")
    private String getPatientData(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");

        H2PMap h2p = h2pMapRepo.findById(id);

        User user = userRepo.findById(h2p.getPatientId());

        JSONObject tmp = new JSONObject();

        tmp.put("id",h2p.getId());
        tmp.put("patientName",h2p.getPatientName());
        tmp.put("patientId",h2p.getPatientId());
        tmp.put("hospitalId",h2p.getHospitalId());
        tmp.put("doctorId",h2p.getDoctorId());
        tmp.put("labId",h2p.getLabId());
        tmp.put("patientPhone",h2p.getPatientPhone());
        tmp.put("status",h2p.getStatus());
        tmp.put("admitDate",h2p.getAdmitDate());

        tmp.put("gender",user.getGender());
        tmp.put("dob",user.getDob());
        tmp.put("photo",user.getPhoto());

        return tmp.toString();
    }

    @PostMapping(path="h2p/discharge-patient")
    private void dischargePatient(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");
        h2pMapRepo.deleteById(id);
    }
}
