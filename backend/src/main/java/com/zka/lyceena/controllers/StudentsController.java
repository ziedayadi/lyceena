package com.zka.lyceena.controllers;


import com.zka.lyceena.dao.StudentsJpaRepository;
import com.zka.lyceena.dto.StudentDto;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.services.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/students")
public class StudentsController {

    @Autowired
    private StudentsService studentsService;

    @GetMapping("/")
    public List<Student> findAll(){
        return this.studentsService.findAll();
    }

    @PostMapping("/")
    public void save(@RequestBody StudentDto student){
        this.studentsService.save(student);
    }


    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id){
        this.studentsService.deleteById(id);
    }
}
