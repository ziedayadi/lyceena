package com.zka.lyceena.dto.attendance;

import com.zka.lyceena.dto.ClassMaterialSessionDto;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SessionAttendanceDto {
    private ClassMaterialSessionDto classMaterialSession;
    private List<StudentAttendanceDto> students;
    private Date date;
}
