package com.zka.lyceena.dto.attendance;

import com.zka.lyceena.dto.ClassDto;
import lombok.Data;

import java.util.List;

@Data
public class SessionAttendanceDto {
    private ClassDto classDto;
    private List<StudentAttendanceDto> students;
}
