package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Lab;
import com.bdehr.backend.Interface.LabRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class LabService {
    @Autowired
    LabRepository labRepo;

    @PostMapping(path="lab/add-lab")
    public void addLab(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());

        String name = jo.getString("name");
        String hospitalId = jo.getString("hospitalId");
        String password = jo.getString("password");

        Lab lab = new Lab(name, hospitalId, password);

        String customId;
        while(true) {
            UUID uuid = UUID.randomUUID();
            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
            if(labRepo.findById(customId) == null){
                break;
            }
        }

        lab.setId(customId);
        labRepo.saveAndFlush(lab);
    }

    @PostMapping(path="lab/login")
    public Lab labLogin(@RequestParam Map<String, String> user) {
        String id = user.get("id");
        String hospitalId = user.get("hospitalId");
        String password = user.get("password");

        Lab tmp = null;
        tmp = labRepo.findByIdAndHospitalIdAndPassword(id, hospitalId, password);
        System.out.println("Lab Login: "+tmp);
        return tmp;
    }

    @PostMapping(path="lab/get-lab-list")
    public List<Lab> getLabList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String hospitalId = jo.getString("hospitalId");

        return labRepo.findByHospitalId(hospitalId);
    }
}
