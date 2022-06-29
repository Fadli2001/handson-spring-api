package com.enigma.simplebackend.controller;

import com.enigma.simplebackend.service.UpdateRecords;
import com.enigma.simplebackend.util.WebResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resetrecords")
public class SeedingController {

    @Autowired
    UpdateRecords updateRecords;

    @GetMapping
    public ResponseEntity<WebResponse<String>> setUp(){
        return ResponseEntity.ok().body(new WebResponse<>(updateRecords.setUp(),null));
    }

}
