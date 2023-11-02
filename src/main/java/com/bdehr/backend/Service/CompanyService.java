package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Company;
import com.bdehr.backend.Entity.Hospital;
import com.bdehr.backend.Interface.CompanyRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class CompanyService {

    @Autowired
    CompanyRepository companyRepo;

    @PostMapping(path="company/register")
    public String addCompany(@RequestParam Map<String, String> map){
        String name = map.get("name");
        String password = map.get("password");
        String email = map.get("email");
        String phone = map.get("phone");
        String factoryAddress = map.get("factoryAddress");
        String factoryAbout = map.get("factoryAbout");
        String logo = map.get("logo");
        String latitude = map.get("latitude");
        String longitude = map.get("longitude");

        Company tmp = null;
        tmp = companyRepo.findByName(name);

        if(tmp!=null)  return "0";

        Company company = new Company(name,password,email,factoryAddress,factoryAbout,latitude,longitude);

        String customId;
        while(true){
            UUID uuid = UUID.randomUUID();
            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
            if(companyRepo.findById(customId) == null){
                break;
            }
        }

        company.setId(customId);

        companyRepo.saveAndFlush(company);
        System.out.println("Company Signup " + company);
        return company.getId();
    }

    @PostMapping(path="company/change-image-logo")
    public void changeFactoryImage(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");
        String urlPhoto = jo.getString("urlPhoto");
        String urlLogo = jo.getString("urlLogo");

        Optional<Company> tmp = Optional.ofNullable(companyRepo.findById(id));
        if(tmp.isPresent()){
            tmp.get().setFactoryImage(urlPhoto);
            tmp.get().setLogo(urlLogo);
            companyRepo.saveAndFlush(tmp.get());
            System.out.println("Set FactoryImage: "+ urlPhoto);
            System.out.println("Set Logo: "+ urlLogo);
        }
    }

    @PostMapping(path="company/login")
    public Company loginCompany(@RequestParam Map<String, String> map){
        String id = map.get("id");
        String password = map.get("password");

        Company tmp = null;
        tmp = companyRepo.findByIdAndPassword(id, password);
        System.out.println("Company Login: "+tmp);
        return tmp;
    }

}
