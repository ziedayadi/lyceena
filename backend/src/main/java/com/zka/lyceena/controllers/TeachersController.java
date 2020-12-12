package com.zka.lyceena.controllers;


import com.zka.lyceena.constants.StaticData;
import com.zka.lyceena.dao.TeachersJpaRepository;
import com.zka.lyceena.entities.actors.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/teachers")
public class TeachersController {


    @Autowired
    private TeachersJpaRepository teachersJpaRepository;

    @GetMapping("/")
    public List<Professor> findAll(){
        return this.teachersJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName","lastName"));
    }
}
