package com.zka.lyceena.controllers;


import com.zka.lyceena.dto.attendance.SessionAttendanceDto;
import com.zka.lyceena.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/teacher/currentSession")
    public SessionAttendanceDto getCurrentSessionAttendance(){
        return attendanceService.getCurrentSessionForTeacher();
    }
}
