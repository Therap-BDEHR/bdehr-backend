package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Report;
import com.bdehr.backend.Interface.ReportRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class ReportService {
    @Autowired
    ReportRepository reportRepo;

    @PostMapping(path="report/add-report")
    public void addReport(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String labId = jo.getString("labId");
        String hospitalId = jo.getString("hospitalId");
        String patientId = jo.getString("patientId");
        String hospitalName = jo.getString("hospitalName");
        String url = jo.getString("url");
        String created_at = java.time.LocalDate.now().toString();

        Report report = new Report(patientId,hospitalId,hospitalName,labId,created_at,url);
        String customId;
        while(true) {
            UUID uuid = UUID.randomUUID();
            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
            if(reportRepo.findById(customId) == null){
                break;
            }
        }
        //java.time.LocalDate.now().toString()
        report.setId(customId);
        reportRepo.saveAndFlush(report);
    }

    @PostMapping(path="report/get-report-list")
    public List<Report> getReportList(HttpEntity<String> httpEntity) {
        JSONObject jo = new JSONObject(httpEntity.getBody());
        String patientId = jo.getString("patientId");
        return reportRepo.findByPatientId(patientId);
    }

}
