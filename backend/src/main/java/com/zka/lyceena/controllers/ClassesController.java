package com.zka.lyceena.controllers;

import com.zka.lyceena.dao.ClassMaterialSessionDto;
import com.zka.lyceena.dto.ClassDto;
import com.zka.lyceena.dto.ClassLevelRefDto;
import com.zka.lyceena.dto.TeacherDto;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.actors.Teacher;
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

    @GetMapping("/{id}")
    public Class findOne(@PathVariable("id") Long id) {
        return this.classesService.findOne(id);
    }

    @GetMapping("/{id}/students")
    public List<Student> findStudentsByClassId(@PathVariable("id") Long id) {
        return this.classesService.findStudentsByClassId(id);
    }

    @GetMapping("/{id}/teachers")
    public List<TeacherDto> findTeachersByClassId(@PathVariable("id") Long id) {
        return this.classesService.findTeachersByClassId(id);
    }

    @GetMapping("/{id}/sessions")
    public List<ClassMaterialSessionDto> findSessionsByClassId(@PathVariable("id") Long id) {
        return this.classesService.findSessionsByClassId(id);
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
