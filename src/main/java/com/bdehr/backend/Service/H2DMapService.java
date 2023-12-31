package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Doctor;
import com.bdehr.backend.Entity.H2DMap;
import com.bdehr.backend.Entity.Hospital;
import com.bdehr.backend.Interface.DoctorRepository;
import com.bdehr.backend.Interface.H2DMapRepository;
import com.bdehr.backend.Interface.HospitalRepository;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class H2DMapService {
    @Autowired
    private H2DMapRepository h2dMapRepo;

    @Autowired
    DoctorRepository doctorRepo;

    @Autowired
    HospitalRepository hospitalRepo;

    @PostMapping(path="h2d/add-doctor")
    public String addDoctor(@RequestParam Map<String, String> map){
        String hospitalId = map.get("hospitalId");
        String doctorId = map.get("doctorId");
        String speciality = map.get("speciality");
        String degree = map.get("degree");

        Doctor tmp = null;
        tmp = doctorRepo.findById(doctorId);
        if(tmp==null) return "0";

        H2DMap h2d = new H2DMap(hospitalId, doctorId, speciality, degree);

        String customId;
        while(true) {
            UUID uuid = UUID.randomUUID();
            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
            if(h2dMapRepo.findById(customId) == null){
                break;
            }
        }

        h2d.setId(customId);

        h2dMapRepo.saveAndFlush(h2d);
        System.out.println("H2D Add Doctor: "+ h2d);
        return "1";
    }

    @PostMapping(path="h2d/get-doctor-list")
    public List<String> getDoctorList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String hospitalId = jo.getString("hospitalId");

        List<H2DMap> h2dList = new ArrayList<>();

        h2dList = h2dMapRepo.findByHospitalId(hospitalId);

        List<String> stringList = new ArrayList<>();

        for(H2DMap h2d : h2dList){
            Doctor doctor = doctorRepo.findById(h2d.getDoctorId());

            JSONObject tmp = new JSONObject();

            tmp.put("id",h2d.getId());
            tmp.put("doctorId",doctor.getId());
            tmp.put("name",doctor.getName());
            tmp.put("photo",doctor.getPhoto());

            tmp.put("speciality",h2d.getSpeciality());
            tmp.put("degree",h2d.getDegree());

            stringList.add(tmp.toString());
        }

        return stringList;
    }

    @PostMapping(path="h2d/get-doctor-cnt")
    public int getDoctorCnt(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String hospitalId = jo.getString("hospitalId");

        List<H2DMap> h2dList = h2dMapRepo.findByHospitalId(hospitalId);
//        System.out.println("Doctor Count: "+h2dList.size());
        return h2dList.size();
    }

    @PostMapping(path="h2d/get-hospital-list")
    public List<Hospital> getHospitalList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String doctorId = jo.getString("doctorId");

        List<H2DMap> h2dList = new ArrayList<>();
        List<Hospital> hospitalList = new ArrayList<>();

        h2dList = h2dMapRepo.findByDoctorId(doctorId);

        for(H2DMap h2d : h2dList){
            Hospital hospital = hospitalRepo.findById(h2d.getHospitalId());
            hospitalList.add(hospital);
        }

        return hospitalList;
    }

    @Transactional
    @PostMapping(path="h2d/remove-doctor")
    public void removeDoctor(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");
        System.out.println(id);

        h2dMapRepo.deleteById(id);
    }

}
