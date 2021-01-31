package com.zka.lyceena.controllers;


import com.zka.lyceena.dto.ClassMaterialSessionDto;
import com.zka.lyceena.dto.TeacherDto;
import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.services.TeachersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/teachers")
public class TeachersController {


    @Autowired
    private TeachersService teachersService;

    @GetMapping("/")
    public List<TeacherDto> findAll() {
        return this.teachersService.findAll();
    }

    @PostMapping("/")
    public Teacher save(@RequestBody TeacherDto teacher) {
        return this.teachersService.save(teacher);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String teacherId) {
        this.teachersService.delete(teacherId);
    }

    @GetMapping("/timesheet")
    public List<ClassMaterialSessionDto> findSessionsByClassId() {
        return this.teachersService.getTimeSheet();
    }

}
