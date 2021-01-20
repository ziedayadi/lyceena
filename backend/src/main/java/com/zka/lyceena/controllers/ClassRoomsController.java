package com.zka.lyceena.controllers;


import com.zka.lyceena.dto.ClassLevelRefDto;
import com.zka.lyceena.dto.ClassRoomDto;
import com.zka.lyceena.services.ClassRoomsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/class-rooms")
public class ClassRoomsController {

    @Autowired
    private ClassRoomsService classRoomsService;

    @GetMapping("/")
    public List<ClassRoomDto> findAll() {
        return this.classRoomsService.findAll();
    }

    @PostMapping("/")
    public void save(@RequestBody ClassRoomDto dto) {
        this.classRoomsService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        this.classRoomsService.deleteById(id);
    }
}
