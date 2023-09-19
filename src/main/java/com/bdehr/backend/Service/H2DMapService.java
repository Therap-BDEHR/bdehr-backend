package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.H2DMap;
import com.bdehr.backend.Interface.H2DMapRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class H2DMapService {
    @Autowired
    private H2DMapRepository h2dMapRepo;

    @GetMapping(path="api/h2d/addTest")
    public String add(){
        int hospital_id = 10;
        int doctor_id = 20;
        JSONObject jo = new JSONObject();
        jo.put("name", "jon doe");
        jo.put("age", "22");
        jo.put("city", "chicago");

        h2dMapRepo.save(new H2DMap(hospital_id, doctor_id, jo.toString()));
        H2DMap h2DMap = h2dMapRepo.findById(1);
        JSONObject tmp = new JSONObject(h2DMap.getAddress());
        System.out.println(tmp);
        System.out.println(tmp.get("name"));
        return jo.toString();
    }

    @GetMapping(path="test2")
    public List<H2DMap> getTest2(){
        List<H2DMap> h2DMapList = h2dMapRepo.findByDoctorId(20);
        //System.out.println(h2DMapList);
        return h2DMapList;
    }
}
