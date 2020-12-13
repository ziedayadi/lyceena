package com.zka.lyceena.controllers;

import com.zka.lyceena.dao.ClassesJpaRepository;
import com.zka.lyceena.entities.actors.Parent;
import com.zka.lyceena.entities.classes.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/classes")
public class ClassesController {

    @Autowired
    private ClassesJpaRepository classesJpaRepository;

    @GetMapping("/")
    public List<Class> findAll() {
        return this.classesJpaRepository.findAll();
    }
}
