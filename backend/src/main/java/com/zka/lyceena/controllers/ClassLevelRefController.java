package com.zka.lyceena.controllers;

import com.zka.lyceena.dto.ClassLevelRefDto;
import com.zka.lyceena.dto.ParentDto;
import com.zka.lyceena.services.ClassLevelRefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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
}
