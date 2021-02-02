package com.zka.lyceena.controllers;

import com.zka.lyceena.dto.*;
import com.zka.lyceena.entities.ref.ClassLevelRef;
import com.zka.lyceena.services.ClassLevelRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/class-level-ref")
public class ClassLevelRefController {

    @Autowired
    private ClassLevelRefService classLevelRefService;

    @GetMapping("/")
    public List<ClassLevelRefDto> findAll() {
        return this.classLevelRefService.findAll();
    }

    @PostMapping("/")
    public void save(@RequestBody ClassLevelRefDto dto) {
        this.classLevelRefService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        this.classLevelRefService.deleteById(id);
    }

    @GetMapping("/{id}")
    public ClassLevelRefDto findById(@PathVariable("id") Integer id) {
        return this.classLevelRefService.findById(id);
    }

    @GetMapping("/{id}/materials")
    public List<LevelMaterialNumberOfHoursDto> findMaterialsByClassLevelId(@PathVariable("id") Integer id) {
        return this.classLevelRefService.findMaterialsByClassLevelId(id);
    }

    @DeleteMapping("/{levelId}/{materialId}")
    public void deleteClassLevelMaterialByLevelIdMaterialId(@PathVariable("levelId")Integer levelId,@PathVariable("materialId") String materialId){
        this.classLevelRefService.deleteClassLevelMaterialByClassIdMaterialId(levelId, materialId);
    }

    @PostMapping("/{id}/save_material")
    public void updateOrSaveMaterialToLevel(@PathVariable("id") Integer levelId, @RequestBody SaveMaterialToClassLevelDto saveMaterialToClassLevelDto){
        this.classLevelRefService.addMaterialToClassLevel(levelId,  saveMaterialToClassLevelDto);
    }
}
