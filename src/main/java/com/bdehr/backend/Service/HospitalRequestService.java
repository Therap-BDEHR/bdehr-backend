package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Hospital;
import com.bdehr.backend.Entity.HospitalRequest;
import com.bdehr.backend.Interface.HospitalRepository;
import com.bdehr.backend.Interface.HospitalRequestRepository;
import jakarta.transaction.Transactional;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
@Transactional
public class HospitalRequestService{
    @Autowired
    HospitalRequestRepository hospitalRequestRepo;
    @Autowired
    HospitalRepository hospitalRepo;

    @PostMapping(path="hospital-request/request")
    public String addHospitalRequest(@RequestParam Map<String, String> map){
        String name = map.get("name");
        String password = map.get("password");
        String authPassword = map.get("authPassword");
        String dob = map.get("dob");
        String address = map.get("address");
        String longitude = map.get("longitude");
        String latitude = map.get("latitude");
//        String photo = map.get("photo");
//        String logo = map.get("logo");
        String phone = map.get("phone");
        String email = map.get("email");
        int numDept = Integer.parseInt(map.get("numDept"));
        String deptList = map.get("deptList");
        String labList = map.get("labList");

        HospitalRequest tmp1 = null;
        tmp1 = hospitalRequestRepo.findByName(name);
        if(tmp1!=null) return "0";

        Hospital tmp2 = null;
        tmp2 = hospitalRepo.findByName(name);
        if(tmp2!=null) return "0";

        HospitalRequest hospitalRequest = new HospitalRequest(name, password, authPassword, dob, address, longitude, latitude, phone, email, numDept, deptList, labList);

        String customId;
        while(true) {
            UUID uuid = UUID.randomUUID();
            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
            if(hospitalRequestRepo.findById(customId) == null && hospitalRepo.findById(customId) == null){
                break;
            }
        }

        hospitalRequest.setId(customId);

        hospitalRequestRepo.saveAndFlush(hospitalRequest);
        System.out.println("Hospital Request: "+hospitalRequest);
        return hospitalRequest.getId();
    }

    @PostMapping(path="hospital-request/change-photo-logo")
    public void changePhoto(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");
        String urlPhoto = jo.getString("urlPhoto");
        String urlLogo = jo.getString("urlLogo");

        Optional<HospitalRequest> tmp = Optional.ofNullable(hospitalRequestRepo.findById(id));
        if(tmp.isPresent()){
            tmp.get().setPhoto(urlPhoto);
            tmp.get().setLogo(urlLogo);
            hospitalRequestRepo.saveAndFlush(tmp.get());
            System.out.println("Set Photo: "+ urlPhoto);
            System.out.println("Set Logo: "+ urlLogo);
        }
    }

    @PostMapping(path="hospital-request/accept")
    public void acceptHospitalRequest(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");

        HospitalRequest hospitalRequest = hospitalRequestRepo.findById(id);

        hospitalRepo.saveAndFlush(new Hospital(
                hospitalRequest.getId(),
                hospitalRequest.getName(),
                hospitalRequest.getPassword(),
                hospitalRequest.getAuthPassword(),
                hospitalRequest.getDob(),
                hospitalRequest.getAddress(),
                hospitalRequest.getLongitude(),
                hospitalRequest.getLatitude(),
                hospitalRequest.getPhoto(),
                hospitalRequest.getLogo(),
                hospitalRequest.getPhone(),
                hospitalRequest.getEmail(),
                hospitalRequest.getNumDept(),
                hospitalRequest.getDeptList(),
                hospitalRequest.getLabList()
        ));

        hospitalRequestRepo.deleteById(id);
        System.out.println("Accept Request: "+id);
    }

    @PostMapping(path="hospital-request/decline")
    public void declineHospitalRequest(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String id = jo.getString("id");
        hospitalRequestRepo.deleteById(id);
        System.out.println("Decline Request: "+id);
    }

    @GetMapping(path="hospital-request/get-request-list")
    public List<HospitalRequest> getRequestList(){
        return hospitalRequestRepo.findAll();
    }

    @GetMapping(path="hospital-request/get-request-cnt")
    public int getRequestListSize(){
        return hospitalRequestRepo.findAll().size();
    }
}
