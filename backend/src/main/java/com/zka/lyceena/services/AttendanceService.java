package com.zka.lyceena.services;

import com.zka.lyceena.dto.attendance.SaveSessionText;
import com.zka.lyceena.dto.attendance.SaveStudentAttendanceDto;
import com.zka.lyceena.dto.attendance.SessionAttendanceDto;
import com.zka.lyceena.dto.attendance.SessionAttendanceGlobalInformationDto;
import com.zka.lyceena.entities.ref.SessionAttendanceStatusValue;

import java.util.List;

public interface AttendanceService {
    SessionAttendanceDto getCurrentSessionForTeacher();

    SessionAttendanceDto saveStudentAttendanceForSessionByTeacher(SaveStudentAttendanceDto saveStudentAttendance);

    SessionAttendanceDto updateStatus(Long sessionAttendanceId, SessionAttendanceStatusValue sent);

    SessionAttendanceDto saveSessionText(SaveSessionText saveSessionText);

    List<SessionAttendanceGlobalInformationDto> getSessionForTeacher();
}
