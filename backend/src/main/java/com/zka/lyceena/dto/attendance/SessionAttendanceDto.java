package com.zka.lyceena.dto.attendance;

import com.zka.lyceena.dto.ClassMaterialSessionDto;
import com.zka.lyceena.entities.ref.SessionAttendanceStatusValue;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class SessionAttendanceDto {
    private Long id;
    private ClassMaterialSessionDto classMaterialSession;
    private List<StudentAttendanceDto> students;
    private Date date;
    private SessionAttendanceStatusValue status;
    private String sessionText;
}
