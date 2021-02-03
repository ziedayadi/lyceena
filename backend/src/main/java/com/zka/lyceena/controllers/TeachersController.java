package com.zka.lyceena.controllers;


import com.zka.lyceena.dto.ClassDto;
import com.zka.lyceena.dto.ClassMaterialSessionDto;
import com.zka.lyceena.dto.TeacherDto;
import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.services.TeachersService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
public class TeachersController {


    @Autowired
    private ModelMapper modelMapper;

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

    @GetMapping("/{id}/classes")
    public List<ClassDto> findClassesByTeacherId(@PathVariable("id") String teacherId) {
        List<Class> classes = this.teachersService.findClassesByTeacherId(teacherId);
        return classes.stream()
                .map(c-> this.modelMapper.map(c, ClassDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/timesheet")
    public List<ClassMaterialSessionDto> findSessionsByClassId() {
        return this.teachersService.getTimeSheet();
    }

    @GetMapping("/{id}")
    public TeacherDto findOne(@PathVariable("id")String id){
        Teacher t = this.teachersService.findOne(id);
        return this.modelMapper.map(t, TeacherDto.class);
    }

}
