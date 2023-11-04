package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Company;
import com.bdehr.backend.Entity.Drug;
import com.bdehr.backend.Interface.DrugRepository;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
@Transactional
public class DrugService {

    @Autowired
    DrugRepository drugRepo;

    @PostMapping("drug/add-drug")
    public void addDrug(@RequestParam Map<String, String> map){
        String name = map.get("name");
        String variant = map.get("variant");
        String chemicalName = map.get("chemicalName");
        String intensity = map.get("intensity");
        String category = map.get("category");

        String bestBefore = map.get("bestBefore");
        String indications = map.get("indications");
        String pharmacology = map.get("pharmacology");
        String dosage = map.get("dosage");
        String administration = map.get("administration");

        String interaction = map.get("interaction");
        String sideEffects = map.get("sideEffects");
        String precautions = map.get("precautions");
        String storageConditions = map.get("storageConditions");

        int unitSold = 0;

        String company = map.get("company");

        Drug drug = new Drug(name,variant,chemicalName,intensity,category,bestBefore,
                indications,pharmacology,dosage,administration,interaction,sideEffects,
                precautions,storageConditions,unitSold,company);
        drugRepo.saveAndFlush(drug);
    }

    @PostMapping("drug/add-drug-photo")
    public void addDrugPhoto(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String name = jo.getString("name");
        String urlPhoto = jo.getString("urlPhoto");

        Optional<Drug> tmp = Optional.ofNullable(drugRepo.findByName(name));
        if(tmp.isPresent()){
            tmp.get().setPhoto(urlPhoto);
            drugRepo.saveAndFlush(tmp.get());
            System.out.println("Set Drug Photo: "+ urlPhoto);
        }
    }

    @PostMapping("drug/update-drug")
    public void updateDrug(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());

        int id = jo.getInt("id");

        String name = jo.getString("name");
        String variant = jo.getString("variant");
        String chemicalName = jo.getString("chemicalName");
        String intensity = jo.getString("intensity");
        String category = jo.getString("category");

        String bestBefore = jo.getString("bestBefore");
        String indications = jo.getString("indications");
        String pharmacology = jo.getString("pharmacology");
        String dosage = jo.getString("dosage");
        String administration = jo.getString("administration");

        String interaction = jo.getString("interaction");
        String sideEffects = jo.getString("sideEffects");
        String precautions = jo.getString("precautions");
        String storageConditions = jo.getString("storageConditions");
        String photo = jo.getString("photo");

        Drug drug = drugRepo.findById(id);

        drug.setName(name);
        drug.setVariant(variant);
        drug.setChemicalName(chemicalName);
        drug.setIntensity(intensity);
        drug.setCategory(category);
        drug.setBestBefore(bestBefore);
        drug.setIndications(indications);
        drug.setPharmacology(pharmacology);
        drug.setDosage(dosage);
        drug.setAdministration(administration);
        drug.setInteraction(interaction);
        drug.setSideEffects(sideEffects);
        drug.setPrecautions(precautions);
        drug.setStorageConditions(storageConditions);
        drug.setPhoto(photo);

        drugRepo.saveAndFlush(drug);
    }

    @PostMapping("drug/get-drug-list")
    public List<Drug> getDrugList(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String company = jo.getString("company");
        return drugRepo.findByCompany(company);
    }

    @GetMapping("drug/get-all-drugs")
    public List<Drug> getAllDrugs(){
        return drugRepo.findAll();
    }

    @PostMapping("drug/increase-unitSold")
    public void increaseUnitSold(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String name = jo.getString("name");
        Drug drug = drugRepo.findByName(name);

        drug.setUnitSold(drug.getUnitSold()+1);
        drugRepo.saveAndFlush(drug);
    }

    @PostMapping("drug/remove-drug")
    public void removeDrug(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        int id = jo.getInt("id");
        drugRepo.deleteById(id);
    }
}
