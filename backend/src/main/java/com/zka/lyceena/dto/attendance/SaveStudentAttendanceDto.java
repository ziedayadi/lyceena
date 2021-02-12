package com.zka.lyceena.dto.attendance;


import com.zka.lyceena.entities.ref.StudentAttendanceValue;
import lombok.Data;

@Data
public class SaveStudentAttendanceDto {

    private Long sessionAttendanceId;
    private String studentId;
    private StudentAttendanceValue studentAttendance;

}
