package com.zka.lyceena.controllers;


import com.zka.lyceena.entities.material.Material;
import com.zka.lyceena.services.MaterialRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping(value = "ref/materials", produces = MediaType.APPLICATION_JSON_VALUE)
public class MaterialReferenceController {

    @Autowired
    private MaterialRefService materialRefService;

    @GetMapping("/")
    public List<Material> findAll() {
        return this.materialRefService.findAll();
    }
}
