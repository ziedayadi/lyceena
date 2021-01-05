package com.zka.lyceena.controllers;

import com.zka.lyceena.dto.ParentDto;
import com.zka.lyceena.entities.actors.Parent;
import com.zka.lyceena.services.ParentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/parents")
public class ParentsController {

    @Autowired
    private ParentsService parentsService;

    @GetMapping("/")
    public List<Parent> findAll() {
        return this.parentsService.findAll();
    }

    @PostMapping("/")
    public void save(@RequestBody ParentDto parentDto) {
        this.parentsService.save(parentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        this.parentsService.deleteById(id);
    }
}
