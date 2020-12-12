package com.zka.lyceena.controllers;

import com.zka.lyceena.constants.StaticData;
import com.zka.lyceena.dao.ParentsJpaRepository;
import com.zka.lyceena.entities.actors.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/parents")
public class ParentsController {

    @Autowired
    private ParentsJpaRepository parentsJpaRepository;

    @GetMapping("/")
    public List<Parent> findAll() {
        return this.parentsJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName","lastName"));
    }
}
