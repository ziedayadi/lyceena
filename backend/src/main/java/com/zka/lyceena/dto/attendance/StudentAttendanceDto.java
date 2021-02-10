package com.zka.lyceena.dto.attendance;

import com.zka.lyceena.dto.StudentDto;
import com.zka.lyceena.entities.ref.StudentAttendanceValue;
import lombok.Data;

@Data
public class StudentAttendanceDto {
    private StudentDto student;
    private StudentAttendanceValue presence;
}
