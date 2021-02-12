package com.zka.lyceena.services;

import com.zka.lyceena.dto.attendance.SaveStudentAttendanceDto;
import com.zka.lyceena.dto.attendance.SessionAttendanceDto;

public interface AttendanceService {
    SessionAttendanceDto getCurrentSessionForTeacher();

    SessionAttendanceDto saveStudentAttendanceForSessionByTeacher(SaveStudentAttendanceDto saveStudentAttendance);
}
