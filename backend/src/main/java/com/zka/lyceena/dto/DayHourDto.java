package com.zka.lyceena.dto;

import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.HourDayRef;
import com.zka.lyceena.services.ClassesServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;


@Data
@AllArgsConstructor
public class DayHourDto {

    DayWeekRef day;
    HourDayRef hour;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DayHourDto dayHour = (DayHourDto) o;
        return this.day.getId().equals(dayHour.day.getId()) &&
                this.hour.getId().equals(dayHour.hour.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, hour);
    }
}
