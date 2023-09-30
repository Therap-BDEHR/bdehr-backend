package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.DataRequest;
import com.bdehr.backend.Interface.DataRequestRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class DataRequestService {
    @Autowired
    DataRequestRepository dataRequestRepo;

    @PostMapping(path="data/add-request")
    public void addRequest(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String sender = jo.getString("sender");
        String reqText = jo.getString("reqText");
        String reqTime = java.time.LocalDate.now().toString();
        String status = "pending";

        DataRequest dataRequest = new DataRequest(sender,reqText,reqTime,status);
        dataRequestRepo.saveAndFlush(dataRequest);
    }

    @PostMapping(path="data/add-response")
    public void addResponse(HttpEntity<String> httpEntity){
        JSONObject jo = new JSONObject(httpEntity.getBody());
        int id = jo.getInt("id");
        String resText = jo.getString("resText");
        String resTime = java.time.LocalDate.now().toString();
        String url = jo.getString("url");
        String status = "completed";

        DataRequest dataRequest = dataRequestRepo.findById(id);

        dataRequest.setResText(resText);
        dataRequest.setResTime(resTime);
        dataRequest.setUrl(url);
        dataRequest.setStatus(status);

        dataRequestRepo.saveAndFlush(dataRequest);
    }

    @GetMapping(path="data/get-request-list")
    public List<DataRequest> getRequestList() {
        return dataRequestRepo.findByStatus("pending");
    }

    @PostMapping(path="data/get-all")
    public List<DataRequest> getAll(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String sender = jo.getString("sender");
        return dataRequestRepo.findBySender(sender);
    }
}
