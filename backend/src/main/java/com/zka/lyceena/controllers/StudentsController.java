package com.zka.lyceena.controllers;


import com.zka.lyceena.dto.StudentDto;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.services.StudentsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/")
    public List<Student> findAll() {
        return this.studentsService.findAll();
    }

    @PostMapping("/")
    public void save(@RequestBody StudentDto student) {
        this.studentsService.save(student);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        this.studentsService.deleteById(id);
    }

    @GetMapping("/parent/{parentId}")
    public List<StudentDto> findByParentId(@PathVariable("parentId") String parentId){
        return this.studentsService.findByParentId(parentId)
                .stream()
                .map(s-> this.modelMapper.map(s, StudentDto.class))
                .collect(Collectors.toList());
    }
}
