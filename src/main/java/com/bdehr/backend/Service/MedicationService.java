package com.bdehr.backend.Service;

import com.bdehr.backend.Interface.MedicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class MedicationService {
    @Autowired
    private MedicationRepository medicationRepo;
}
