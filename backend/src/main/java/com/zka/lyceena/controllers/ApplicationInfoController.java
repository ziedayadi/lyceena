package com.zka.lyceena.controllers;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/info", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationInfoController {

    @GetMapping("/")
    public Map info(){
        Map<String, String> out = new HashMap<>();
        out.put("status", "UP");
        return out;
    }
}
