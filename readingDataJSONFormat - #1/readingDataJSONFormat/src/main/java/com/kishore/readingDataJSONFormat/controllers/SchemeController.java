package com.kishore.readingDataJSONFormat.controllers;

import com.kishore.readingDataJSONFormat.models.SchemeDTO;
import com.kishore.readingDataJSONFormat.repository.SchemeRepository;
import com.kishore.readingDataJSONFormat.service.SchemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

@RestController
public class SchemeController {

    @Autowired
    SchemeService schemeService;

    public SchemeController(SchemeService schemeService){
        this.schemeService = schemeService;
    }
    @GetMapping(path = "/__/schemes")
    public List<SchemeDTO> getFeeds(){
        return schemeService.getSchemes();
    }

    @PutMapping(path = "/__/receiveSchemes")
    public String getAllSchemes() throws ParserConfigurationException, IOException, SAXException {
        schemeService.schemesSave();
        return "Data Stored into DataBase";
    }

    @GetMapping(path = "/{scheme_id}", consumes = MediaType.ALL_VALUE)
    public Pair<SchemeRepository, Boolean> getSchemesById(@PathVariable(name = "scheme_id") long scheme_Id){
        var schemeDataFound = schemeService.getAllSchemesById(scheme_Id);
        return schemeDataFound;
    }


   /* @GetMapping(path = "/schemes")
    public ResponseEntity<Scheme> schemeDetails(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Scheme> scheme = restTemplate.getForEntity("https://api.mfapi.in/mf", Scheme.class);
        return scheme;
    }*/
}
