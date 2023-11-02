package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.DrugUsage;
import com.bdehr.backend.Interface.DrugUsageRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class DrugUsageService {

    @Autowired
    DrugUsageRepository drugUsageRepo;

    @PostMapping("drug-usage/add-usage")
    public void addUsage(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String name = jo.getString("name");
        String variant = jo.getString("variant");
        String generic = jo.getString("generic");
        String date = "date";
        String userId = jo.getString("userId");
        String company = jo.getString("company");
        String hospital = jo.getString("hospital");
        String latitude = jo.getString("latitude");
        String longitude = jo.getString("longitude");

        DrugUsage drugUsage = new DrugUsage(name,variant,generic,date,userId,company,hospital,latitude,longitude);
        drugUsageRepo.saveAndFlush(drugUsage);
    }

//    @PostMapping("drug-usage/get-query-list")
//    public List<DrugUsage> getQueryList(@RequestParam Map<String, String> map){
//
//    }

}
