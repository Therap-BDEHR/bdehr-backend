package com.bdehr.backend.Service;

import com.bdehr.backend.Interface.H2DMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class H2DMapService {
    @Autowired
    private H2DMapRepository h2dMapRepo;
}
