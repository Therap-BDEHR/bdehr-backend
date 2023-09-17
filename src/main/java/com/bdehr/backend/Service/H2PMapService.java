package com.bdehr.backend.Service;

import com.bdehr.backend.Interface.H2PMapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*" )
@RestController
public class H2PMapService {
    @Autowired
    private H2PMapRepository h2pMapRepo;
}
