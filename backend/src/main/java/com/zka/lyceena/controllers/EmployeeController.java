package com.zka.lyceena.controllers;

import com.zka.lyceena.dto.EmployeeDto;
import com.zka.lyceena.entities.actors.Employee;
import com.zka.lyceena.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeesService employeesService;

    @GetMapping("/")
    public List<Employee> findAll() {
        return employeesService.findAll();
    }

    @PostMapping("/")
    public void save(@RequestBody EmployeeDto dto) {
        this.employeesService.save(dto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") String id) {
        this.employeesService.deleteById(id);
    }
}
