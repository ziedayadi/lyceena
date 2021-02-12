package com.zka.lyceena.controllers;


import com.zka.lyceena.dto.attendance.SaveStudentAttendanceDto;
import com.zka.lyceena.dto.attendance.SessionAttendanceDto;
import com.zka.lyceena.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @GetMapping("/teacher/currentSession")
    public SessionAttendanceDto getCurrentSessionAttendance(){
        return attendanceService.getCurrentSessionForTeacher();
    }

    @PostMapping("teacher/student/attendance")
    public SessionAttendanceDto saveStudentAttendanceForSessionByTeacher(@RequestBody SaveStudentAttendanceDto saveStudentAttendance){
        return this.attendanceService.saveStudentAttendanceForSessionByTeacher(saveStudentAttendance);
    }
}
