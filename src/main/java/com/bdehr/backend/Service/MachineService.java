package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Machine;
import com.bdehr.backend.Interface.MachineRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class MachineService {
    @Autowired
    MachineRepository machineRepo;

    @PostMapping(path="machine/add-machine")
    public int addMachine(@RequestParam Map<String, String> map) {
        String name = map.get("name");
        String labId = map.get("labId");
        String model = map.get("model");
        String company = map.get("company");
        String iDate = map.get("iDate");

        Machine machine = new Machine(name, labId, model, company, iDate);
        System.out.println("Add Machine: "+machine);
        Machine tmp = machineRepo.saveAndFlush(machine);

        return tmp.getId();
    }

    @PostMapping(path="machine/change-photo")
    public void changePhoto(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());
        int id = Integer.parseInt(jo.getString("id"));
        String url = jo.getString("url");

        Optional<Machine> tmp = Optional.ofNullable(machineRepo.findById(id));
        if(tmp.isPresent()){
            tmp.get().setPhoto(url);
            machineRepo.saveAndFlush(tmp.get());
            System.out.println("Set Machine Photo: "+ url);
        }
    }

    @PostMapping(path="machine/get-machine-list")
    public List<Machine> getMachineList(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String labId = jo.getString("labId");

        return machineRepo.findByLabId(labId);
    }
}
