package com.bdehr.backend.Service;

import com.bdehr.backend.Entity.Report;
import com.bdehr.backend.Interface.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class ReportService {
    @Autowired
    private ReportRepository reportRepo;

    @PostMapping(path="report/add-report")
    public void addReport(@RequestParam Map<String, String> map) {
        Report report = new Report();
        String customId;
        while(true) {
            UUID uuid = UUID.randomUUID();
            customId = uuid.toString().substring(0, 8); // Take the first 8 characters
            if(reportRepo.findById(customId) == null){
                break;
            }
        }
        report.setId(customId);
        reportRepo.saveAndFlush(report);
    }

}
