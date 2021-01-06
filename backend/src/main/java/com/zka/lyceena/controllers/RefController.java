package com.zka.lyceena.controllers;

import com.zka.lyceena.dao.EmployeeRefTypeJpaRepository;
import com.zka.lyceena.entities.ref.EmployeeTypeRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/ref")
public class RefController {

    @Autowired
    private EmployeeRefTypeJpaRepository employeeRefTypeJpaRepository;

    @GetMapping("/employees-type")
    public List<EmployeeTypeRef> findAllEmployeesTypeRef() {
        return this.employeeRefTypeJpaRepository.findAll();
    }
}
