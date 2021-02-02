package com.zka.lyceena.controllers;

import com.zka.lyceena.entities.ref.ClassYear;
import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.EmployeeTypeRef;
import com.zka.lyceena.entities.ref.HourDayRef;
import com.zka.lyceena.services.RefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ref")
public class RefController {

    @Autowired
    private RefService refService;

    @GetMapping("/employees-type")
    public List<EmployeeTypeRef> findAllEmployeesTypeRef() {
        return this.refService.findAllEmployeeTypeRef();
    }

    @GetMapping("/days")
    public List<DayWeekRef> findDays() {
        return this.refService.findAllDays();
    }

    @GetMapping("/hours")
    public List<HourDayRef> findHours() {
        return this.refService.findAllHours();
    }

    @GetMapping("/class-years")
    public List<ClassYear> findClassYears() {
        return this.refService.findAllClassYears();
    }
}
