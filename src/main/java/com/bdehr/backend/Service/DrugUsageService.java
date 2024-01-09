package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Drug;
import com.bdehr.backend.Entity.DrugUsage;
import com.bdehr.backend.Entity.Hospital;
import com.bdehr.backend.Interface.DrugRepository;
import com.bdehr.backend.Interface.DrugUsageRepository;
import com.bdehr.backend.Interface.HospitalRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class DrugUsageService {

    @Autowired
    DrugUsageRepository drugUsageRepo;

    @Autowired
    DrugRepository drugRepo;

    @Autowired
    HospitalRepository hospitalRepo;

    @PostMapping("drug-usage/add-usage")
    public void addUsage(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        System.out.println("Drug Usage: "+jo);

        String name = jo.getString("name");
        String variant = jo.getString("variant");
        String generic = jo.getString("generic");
        String date = java.time.LocalDate.now().toString();;
        String patientId = jo.getString("patientId");
        String company = jo.getString("company");
        String hospital = jo.getString("hospital");
        String category = jo.getString("category");

        DrugUsage drugUsage = new DrugUsage(name,variant,generic,date,patientId,company,hospital,category);
        drugUsageRepo.saveAndFlush(drugUsage);

        Drug drug = drugRepo.findByName(name);
        drug.setUnitSold(drug.getUnitSold()+1);
        drugRepo.saveAndFlush(drug);
    }

    @PostMapping("drug-usage/get-query-list")
    public List<String> getQueryList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        System.out.println("Query Param: "+jo);

        String filterBrand = jo.getString("filterBrand");
        String filterVariant = jo.getString("filterVariant");
        String filterCategory = jo.getString("filterCategory");
        String filterChemicalName = jo.getString("filterChemicalName");
        String filterInterval = jo.getString("filterInterval");

        String company = jo.getString("company");

        List<DrugUsage> drugUsageList = drugUsageRepo.findByCompany(company);

        if(!Objects.equals(filterBrand, "")){
            List<DrugUsage> tmpList = new ArrayList<>();

            for(DrugUsage drugUsage : drugUsageList){
                if(drugUsage.getCompany().equalsIgnoreCase(filterBrand)){
                    tmpList.add(drugUsage);
                }
            }

            drugUsageList = tmpList;
        }

        if(!Objects.equals(filterVariant, "")){
            List<DrugUsage> tmpList = new ArrayList<>();

            for(DrugUsage drugUsage : drugUsageList){
                if(drugUsage.getVariant().equalsIgnoreCase(filterVariant)){
                    tmpList.add(drugUsage);
                }
            }

            drugUsageList = tmpList;
        }

        if(!Objects.equals(filterCategory, "")){
            List<DrugUsage> tmpList = new ArrayList<>();

            for(DrugUsage drugUsage : drugUsageList){
                if(drugUsage.getCategory().equalsIgnoreCase(filterCategory)){
                    tmpList.add(drugUsage);
                }
            }

            drugUsageList = tmpList;
        }

        if(!Objects.equals(filterChemicalName, "")){
            List<DrugUsage> tmpList = new ArrayList<>();

            for(DrugUsage drugUsage : drugUsageList){
                if(drugUsage.getGeneric().equalsIgnoreCase(filterChemicalName)){
                    tmpList.add(drugUsage);
                }
            }

            drugUsageList = tmpList;
        }

        if(!Objects.equals(filterInterval, "")) {
            LocalDate currentDate = LocalDate.now();
            LocalDate prevDate = currentDate.minus(Integer.parseInt(filterInterval), ChronoUnit.WEEKS);
            System.out.println("Current Data: "+currentDate+", Previous Data: "+prevDate);
            List<DrugUsage> tmpList = new ArrayList<>();

            for(DrugUsage drugUsage : drugUsageList){
                if(drugUsage.getDate().compareTo( prevDate.toString() ) > 0){
                    tmpList.add(drugUsage);
                }
            }

            drugUsageList = tmpList;
        }

        Map<String, Integer> map = new HashMap<>();

        for(DrugUsage drugUsage : drugUsageList){
            if(map.containsKey(drugUsage.getHospital())){
                map.put(drugUsage.getHospital(),map.get(drugUsage.getHospital())+1);
            }
            else{
                map.put(drugUsage.getHospital(),1);
            }
        }

        List<String> resList = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            JSONObject tmp = new JSONObject();
            tmp.put("name", entry.getKey());
            tmp.put("count",entry.getValue());

            Hospital hospital = hospitalRepo.findByName(entry.getKey());

            tmp.put("latitude", hospital.getLatitude());
            tmp.put("longitude", hospital.getLongitude());

            resList.add(tmp.toString());
        }
        return resList;
    }

}
