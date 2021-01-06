package com.zka.lyceena.controllers;

import com.zka.lyceena.dto.ClassDto;
import com.zka.lyceena.dto.ClassLevelRefDto;
import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.services.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @GetMapping("/")
    public List<Class> findAll() {
        return this.classesService.findAll();
    }

    @PostMapping("/")
    public void save(@RequestBody ClassDto dto) {
        this.classesService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        this.classesService.deleteById(id);
    }
}
