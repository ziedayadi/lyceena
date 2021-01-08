package com.zka.lyceena.dto;

import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.HourDayRef;
import lombok.Data;

@Data
public class ClassMaterialSessionDto {

    private Integer id;
    private ClassDto clazz;
    private MaterialDto material;
    private TeacherDto teacher;
    private DayWeekRef dayOfWeek;
    private HourDayRef startHour;
    private HourDayRef endHour;
    private ClassRoomDto classRoom;
}
