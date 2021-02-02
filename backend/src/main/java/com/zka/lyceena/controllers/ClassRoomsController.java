package com.zka.lyceena.controllers;


import com.zka.lyceena.dto.ClassLevelRefDto;
import com.zka.lyceena.dto.ClassRoomDto;
import com.zka.lyceena.services.ClassRoomsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/class-rooms")
public class ClassRoomsController {

    @Autowired
    private ModelMapper modelMapper;

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

    @GetMapping("/free")
    public List<ClassRoomDto> findForFreeHour(@RequestParam("dayId") Integer dayId, @RequestParam("hourId") Integer hourId){
        return this.classRoomsService.findForFreeHour(dayId, hourId).stream()
                .map(e->modelMapper.map(e,ClassRoomDto.class))
                .collect(Collectors.toList());
    }
}
