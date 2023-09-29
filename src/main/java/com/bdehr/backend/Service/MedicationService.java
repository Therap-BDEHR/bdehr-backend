package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.H2PMap;
import com.bdehr.backend.Entity.Hospital;
import com.bdehr.backend.Entity.Medication;
import com.bdehr.backend.Entity.User;
import com.bdehr.backend.Interface.HospitalRepository;
import com.bdehr.backend.Interface.MedicationRepository;
import com.bdehr.backend.Interface.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class MedicationService {
    @Autowired
    MedicationRepository medicationRepo;

    @Autowired
    HospitalRepository hospitalRepo;

    @Autowired
    UserRepository userRepo;

    @PostMapping(path="medication/add-medication")
    public void addMedication(HttpEntity<String> httpEntity) {
        JSONObject map = new JSONObject(httpEntity.getBody());
        String hospitalId = map.getString("hospitalId");
        String doctorId = map.getString("doctorId");
        String patientId = map.getString("patientId");
        int age = map.getInt("age");
        String gender = map.getString("gender");
        String form = map.getString("form");
        String hospitalName = map.getString("hospitalName");
        String doctorName = map.getString("doctorName");
        String created_at = java.time.LocalDate.now().toString();

        Medication medication = new Medication(patientId,age,gender,hospitalId,doctorId,created_at,"no",form,hospitalName,doctorName);
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

    @PostMapping(path="medication/is-present")
    public String isPresent(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String doctorId = jo.getString("doctorId");
        String hospitalId = jo.getString("hospitalId");
        String patientId = jo.getString("patientId");

        Medication medication = null;
        medication = medicationRepo.findByPatientIdAndDoctorIdAndHospitalIdAndIsDone(patientId,doctorId,hospitalId,"no");

        if(medication==null) return "0";
        else return "1";
    }

    @PostMapping(path="medication/add-others")
    public void addUrl(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String doctorId = jo.getString("doctorId");
        String hospitalId = jo.getString("hospitalId");
        String patientId = jo.getString("patientId");

        String medicationUrl = jo.getString("medicationUrl");
        String diagnosisUrl = jo.getString("diagnosisUrl");
        String problem = jo.getString("problem");

        Medication medication = medicationRepo.findByPatientIdAndDoctorIdAndHospitalIdAndIsDone(patientId,doctorId,hospitalId,"no");

        medication.setMedicationUrl(medicationUrl);
        medication.setDiagnosisUrl(diagnosisUrl);
        medication.setProblem(problem);
        medication.setIsDone("yes");

        medicationRepo.saveAndFlush(medication);
        System.out.println("Others Added: "+jo);
    }

    @PostMapping(path="medication/set-form")
    public void setForm(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String doctorId = jo.getString("doctorId");
        String hospitalId = jo.getString("hospitalId");
        String patientId = jo.getString("patientId");

        String form = jo.getString("form");

        Medication medication = medicationRepo.findByPatientIdAndDoctorIdAndHospitalIdAndIsDone(patientId,doctorId,hospitalId,"no");

        medication.setForm(form);
        medicationRepo.saveAndFlush(medication);
    }

    @PostMapping(path="medication/get-form")
    public String getForm(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String doctorId = jo.getString("doctorId");
        String hospitalId = jo.getString("hospitalId");
        String patientId = jo.getString("patientId");

        Medication medication = medicationRepo.findByPatientIdAndDoctorIdAndHospitalIdAndIsDone(patientId,doctorId,hospitalId,"no");
        return  medication.getForm();
    }

    @PostMapping(path="medication/get-medication-list")
    public List<Medication> getMedicationList(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String patientId = jo.getString("patientId");

        return medicationRepo.findByPatientIdAndIsDone(patientId,"yes");
    }

    @GetMapping(path="medication/get-patient-cnt")
    public List<String> getPatientCnt() {
        List<Medication> medicationList = medicationRepo.findAll();
        List<String> stringList = new ArrayList<>();

        Map<String,Integer> map = new HashMap<>();

        for(Medication medication:medicationList){
            Hospital hospital = hospitalRepo.findById(medication.getHospitalId());

            if(!map.containsKey(hospital.getName())){
                map.put(hospital.getName(), 1);
            }
            else{
                map.put(hospital.getName(), map.get(hospital.getName())+1);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + ":" + entry.getValue());

            Hospital hospital = hospitalRepo.findByName(entry.getKey());
            JSONObject tmp = new JSONObject();
            tmp.put("name",entry.getKey());
            tmp.put("count",entry.getValue());
            tmp.put("longitude",Float.parseFloat(hospital.getLongitude()));
            tmp.put("latitude",Float.parseFloat(hospital.getLatitude()));

            stringList.add(tmp.toString());
        }
        return stringList;
    }

    @GetMapping(path="medication/get-graph-data")
    public List<String> getGraphData() {

        List<Medication> medicationList = medicationRepo.findByIsDone("yes");
        List<String> stringList = new ArrayList<>();

        Map<String,Integer> uniqueDisease = new HashMap<>();
        Map<Integer,Integer> uniqueAge = new HashMap<>();

        for(Medication medication:medicationList){
            uniqueDisease.put(medication.getProblem(), 1);

            User user = userRepo.findById(medication.getPatientId());
            LocalDate ld = LocalDate.parse(user.getDob());
//            System.out.println(Period.between(ld, LocalDate.now()).getYears());
            Integer age = Integer.parseInt(String.valueOf(Period.between(ld, LocalDate.now()).getYears()));
            uniqueAge.put(age,1);
        }
//        System.out.println("Here");
//        System.out.println(uniqueAge);
        for (Map.Entry<Integer, Integer> entry : uniqueAge.entrySet()) {
            Integer age = entry.getKey();

            JSONObject tmp = new JSONObject();
            tmp.put("age",age);
            for (Map.Entry<String, Integer> entry2 : uniqueDisease.entrySet()){
                String disease = entry2.getKey();
//                System.out.println(disease);
                tmp.put(disease,0);
            }

            for(Medication medication:medicationList){
                User user = userRepo.findById(medication.getPatientId());
                LocalDate ld = LocalDate.parse(user.getDob());
                Integer age2 = Integer.parseInt(String.valueOf(Period.between(ld, LocalDate.now()).getYears()));

                if(age2.equals(age)){
                    tmp.put(medication.getProblem(),tmp.getInt(medication.getProblem())+1);
                }
            }

            stringList.add(tmp.toString());
        }
        return stringList;
    }
}
