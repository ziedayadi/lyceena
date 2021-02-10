package com.zka.lyceena.services;

import com.zka.lyceena.entities.ref.*;

import java.util.List;
import java.util.Optional;

public interface RefService {

    ClassYear getCurrentClassYear();
    List<ClassYear> findAllClassYears();
    List<DayWeekRef> findAllDays();
    Optional<DayWeekRef> findDayWeekRefById(Integer id);
    Optional<HourDayRef> findHourDayRefById(Integer id);
    List<HourDayRef> findAllHours();
    List<EmployeeTypeRef> findAllEmployeeTypeRef();
    List<GlobalRef> findAllGlobalRefData();
    HourDayRef getCurrentHourOfDay();
    DayWeekRef getCurrentDayOfWeek();
}
