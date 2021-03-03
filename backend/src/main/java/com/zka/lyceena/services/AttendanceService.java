package com.zka.lyceena.services;

import com.zka.lyceena.dto.attendance.*;
import com.zka.lyceena.entities.ref.SessionAttendanceStatusValue;

import java.util.List;

public interface AttendanceService {
    SessionAttendanceDto getCurrentSessionForTeacher();

    SessionAttendanceDto saveStudentAttendanceForSessionByTeacher(SaveStudentAttendanceDto saveStudentAttendance);

    SessionAttendanceDto updateStatus(Long sessionAttendanceId, SessionAttendanceStatusValue sent);

    SessionAttendanceDto saveSessionText(SaveSessionText saveSessionText);

    List<SessionAttendanceGlobalInformationDto> getSessionForTeacher();

    SessionAttendanceDto getSessionById(Long sessionId);

    List<SessionAttendanceGlobalInformationDto> getAdminSessions();

    List<SessionAttendanceGlobalInformationDto> getSessionForStudent();

    SessionAttendanceDto getSessionForStudentBySessionId(Long sessionId);

    List<StudentAttendanceDto> getAttendanceByStudentId(String studentId);
}
