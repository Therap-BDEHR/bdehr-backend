package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Medication;
import com.bdehr.backend.Interface.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepo;

    @PostMapping(path="medication/add-medication")
    public void addMedication(@RequestParam Map<String, String> map) {
        String hospitalId = map.get("hospitalId");
        String doctorId = map.get("doctorId");
        String userId = map.get("userId");
        int age = Integer.parseInt(map.get("age"));
        String gender = map.get("gender");
        String form = map.get("form");
        String url = map.get("url");
        String created_at = java.time.LocalDate.now().toString();

        Medication medication = new Medication(userId,age,gender,hospitalId,doctorId,created_at,form,url);
        String customId;
        while(true) {
            UUID uuid = UUID.randomUUID();
            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
            if(medicationRepo.findById(customId) == null){
                break;
            }
        }
        medication.setId(customId);
        medicationRepo.saveAndFlush(medication);
    }
}
