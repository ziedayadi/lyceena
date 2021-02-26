package com.zka.lyceena.controllers;


import com.zka.lyceena.dto.attendance.*;
import com.zka.lyceena.entities.ref.SessionAttendanceStatusValue;
import com.zka.lyceena.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("teacher/session/send")
    public SessionAttendanceDto sendSessionForApproval(@RequestBody SendSessionDto sendSessionDto){
        return this.attendanceService.updateStatus(sendSessionDto.getSessionAttendanceId(), SessionAttendanceStatusValue.SENT);
    }

    @PostMapping("teacher/session/text")
    public SessionAttendanceDto saveSessionText(@RequestBody SaveSessionText saveSessionText){
        return this.attendanceService.saveSessionText(saveSessionText);
    }

    @GetMapping("teacher/sessions")
    public List<SessionAttendanceGlobalInformationDto> getTeacherSessions(){
        return this.attendanceService.getSessionForTeacher();
    }
}
