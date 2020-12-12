package com.zka.lyceena.controllers;


import com.zka.lyceena.constants.StaticData;
import com.zka.lyceena.dao.StudentsJpaRepository;
import com.zka.lyceena.entities.actors.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentsJpaRepository studentsJpaRepository;

    @GetMapping("/")
    public List<Student> findAll(){
        return this.studentsJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName","lastName"));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id){
        this.studentsJpaRepository.deleteById(id);
    }
}
