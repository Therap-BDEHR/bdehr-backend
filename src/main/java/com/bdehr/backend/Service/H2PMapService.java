package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Doctor;
import com.bdehr.backend.Entity.H2PMap;
import com.bdehr.backend.Entity.Hospital;
import com.bdehr.backend.Entity.User;
import com.bdehr.backend.Interface.DoctorRepository;
import com.bdehr.backend.Interface.H2PMapRepository;
import com.bdehr.backend.Interface.HospitalRepository;
import com.bdehr.backend.Interface.UserRepository;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class H2PMapService {
    @Autowired
    H2PMapRepository h2pMapRepo;

    @Autowired
    UserRepository userRepo;

    @Autowired
    HospitalRepository hospitalRepo;

    @Autowired
    DoctorRepository doctorRepo;

    @PostMapping(path="h2p/add-patient")
    public String addPatient(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        System.out.println(jo);
        String hospitalId = jo.getString("hospitalId");
        String patientId = jo.getString("patientId");
        String patientName = jo.getString("patientName");
        String patientPhone = jo.getString("patientPhone");
        String status = jo.getString("status");

        User tmp = null;
        tmp = userRepo.findById(patientId);
        if(tmp==null) return "0";

        Hospital tmp2 = null;
        tmp2 = hospitalRepo.findById(hospitalId);
        if(tmp2==null) return "0";

        H2PMap h2p = new H2PMap(hospitalId, patientId, patientName, patientPhone, status, java.time.LocalDate.now().toString());

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
    public List<String> getHospitalPatientList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        System.out.println("Here: "+jo);
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

            tmp.put("labId",h2p.getLabId());
            tmp.put("patientPhone",h2p.getPatientPhone());
            tmp.put("status",h2p.getStatus());
            tmp.put("admitDate",h2p.getAdmitDate());

            tmp.put("dob",user.getDob());
            tmp.put("photo",user.getPhoto());

            if(h2p.getDoctorId()!=null) {
                Doctor doctor = doctorRepo.findById(h2p.getDoctorId());
//                System.out.println(doctor);
                tmp.put("doctorId",h2p.getDoctorId());
                if(doctor!=null) {
                    tmp.put("doctorName", doctor.getName());
                }
            }
            else{
                tmp.put("doctorId","");
                tmp.put("doctorName", "");
            }
            stringList.add(tmp.toString());
        }

        return stringList;
    }

    @PostMapping(path="h2p/get-hospital-patient-cnt")
    public int getHospitalPatientCnt(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String hospitalId = jo.getString("hospitalId");

        List<H2PMap> h2pList = h2pMapRepo.findByHospitalId(hospitalId);
//        System.out.println("Patient Cnt: "+h2pList.size());
        return h2pList.size();
    }

    @PostMapping(path="h2p/get-doctor-patient-list")
    public List<String> getDoctorPatientList(HttpEntity<String> httpEntity){
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
    public List<String> getLabPatientList(HttpEntity<String> httpEntity){
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

    @PostMapping(path="h2p/get-user-patient-list")
    public List<String> getUserPatientList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());

        String patientId = jo.getString("patientId");

        List<H2PMap> h2pList = new ArrayList<>();

        h2pList = h2pMapRepo.findByPatientId(patientId);

        List<String> stringList = new ArrayList<>();

        for(H2PMap h2p : h2pList){
            Hospital hospital= hospitalRepo.findById(h2p.getHospitalId());

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

            tmp.put("hospitalName",hospital.getName());

            stringList.add(tmp.toString());
        }

        return stringList;
    }

    @PostMapping(path="h2p/get-patient-data")
    public String getPatientData(HttpEntity<String> httpEntity){
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

    @Transactional
    @PostMapping(path="h2p/discharge-patient")
    public void dischargePatient(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");
        h2pMapRepo.deleteById(id);
    }

    @PostMapping(path="h2p/update-patient")
    public void updatePatient(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");
        String doctorId = jo.getString("doctorId");
        String labId = jo.getString("labId");
        String status = jo.getString("status");

        H2PMap h2PMap = h2pMapRepo.findById(id);
        h2PMap.setDoctorId(doctorId);
        h2PMap.setStatus(status);
        h2PMap.setLabId(labId);

        h2pMapRepo.saveAndFlush(h2PMap);
    }
}
