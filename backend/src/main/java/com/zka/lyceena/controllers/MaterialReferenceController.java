package com.zka.lyceena.controllers;


import com.zka.lyceena.constants.StaticData;
import com.zka.lyceena.entities.material.Material;
import com.zka.lyceena.services.MaterialRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "ref/materials", produces = MediaType.APPLICATION_JSON_VALUE)
public class MaterialReferenceController {

    @Autowired
    private MaterialRefService materialRefService;

    @GetMapping("/")
    public List<Material> findAll() {
        return this.materialRefService.findAll();
    }

    @PostMapping("/")
    public void save(@RequestBody Material dto){
        this.materialRefService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        this.materialRefService.deleteById(id);
    }
}
