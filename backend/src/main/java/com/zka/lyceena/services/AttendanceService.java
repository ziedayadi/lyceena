package com.zka.lyceena.services;

import com.zka.lyceena.dto.attendance.SessionAttendanceDto;

public interface AttendanceService {
    SessionAttendanceDto getCurrentSessionForTeacher();
}
