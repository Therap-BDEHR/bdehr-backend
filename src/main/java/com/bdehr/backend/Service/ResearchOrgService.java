package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.ResearchOrg;
import com.bdehr.backend.Entity.User;
import com.bdehr.backend.Interface.ResearchOrgRepository;
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
public class ResearchOrgService {
    @Autowired
    ResearchOrgRepository researchOrgRepo;

    @PostMapping(path="research/signup")
    public String addOrg(@RequestParam Map<String, String> map){
        String name = map.get("name");
        String password = map.get("password");
        String email = map.get("email");
        String address = map.get("address");;
        String phone = map.get("phone");

        ResearchOrg tmp = null;
        tmp = researchOrgRepo.findByName(name);

        if(tmp!=null) return "0";

        ResearchOrg researchOrg = new ResearchOrg(name,password,email,address,phone);

        String customId;
        while(true) {
            UUID uuid = UUID.randomUUID();
            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
            if(researchOrgRepo.findById(customId) == null){
                break;
            }
        }

        researchOrg.setId(customId);

        researchOrgRepo.saveAndFlush(researchOrg);
        System.out.println("Org Signup: "+researchOrg);
        return researchOrg.getId();
    }

    @PostMapping(path="research/change-photo")
    public void changePhoto(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");
        String url = jo.getString("url");

        Optional<ResearchOrg> tmp = Optional.ofNullable(researchOrgRepo.findById(id));
        if(tmp.isPresent()){
            tmp.get().setPhoto(url);
            researchOrgRepo.saveAndFlush(tmp.get());
        }
    }

    @PostMapping(path="research/login")
    public ResearchOrg loginOrg(@RequestParam Map<String, String> user){
        String id = user.get("id");
        String password = user.get("password");

        ResearchOrg tmp = null;
        tmp = researchOrgRepo.findByIdAndPassword(id, password);
        System.out.println("Org Login: "+tmp);
        return tmp;
    }
}
