package com.zka.lyceena.controllers;


import com.zka.lyceena.constants.StaticData;
import com.zka.lyceena.dao.StudentsJpaRepository;
import com.zka.lyceena.entities.actors.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@CrossOrigin(StaticData.CROSS_ORIGIN_URL)
@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentsJpaRepository studentsJpaRepository;

    @GetMapping("/")
    public List<Student> findAll(){
        return this.studentsJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName","lastName"));
    }
}
