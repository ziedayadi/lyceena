package com.zka.lyceena.dto.attendance;

import com.zka.lyceena.dto.ClassMaterialSessionDto;
import com.zka.lyceena.entities.ref.SessionAttendanceStatusValue;
import lombok.Data;

import java.util.Date;

@Data
public class SessionAttendanceGlobalInformationDto {
    private Long id;
    private ClassMaterialSessionDto classMaterialSession;
    private Date date;
    private SessionAttendanceStatusValue status;
    private String sessionText;
}
