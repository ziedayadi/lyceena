package com.zka.lyceena.controllers;

import com.zka.lyceena.dao.DayWeekRefJpaRepository;
import com.zka.lyceena.dao.EmployeeRefTypeJpaRepository;
import com.zka.lyceena.dao.HourDayRefJpaRepository;
import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.EmployeeTypeRef;
import com.zka.lyceena.entities.ref.HourDayRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private DayWeekRefJpaRepository dayWeekRefJpaRepository;

    @Autowired
    private HourDayRefJpaRepository hourDayRefJpaRepository;

    @GetMapping("/employees-type")
    public List<EmployeeTypeRef> findAllEmployeesTypeRef() {
        return this.employeeRefTypeJpaRepository.findAll();
    }

    @GetMapping("/days")
    public List<DayWeekRef> findDays() {
        return this.dayWeekRefJpaRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    @GetMapping("/hours")
    public List<HourDayRef> findHours() {
        return this.hourDayRefJpaRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }
}
