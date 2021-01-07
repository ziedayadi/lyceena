package com.zka.lyceena.entities.classes;


import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.entities.material.Material;
import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.HourDayRef;

import javax.persistence.*;
import java.time.DayOfWeek;

@Entity
@Table(name = "SESSION")
public class ClassMaterialSession {

    @Column(name = "ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "CLASS_ID")
    private Class clazz;

    @ManyToOne
    @JoinColumn(name = "MATERIAL_ID")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "PROFESSOR_ID")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "DAY_OF_WEEK")
    private DayWeekRef dayOfWeek;

    @ManyToOne
    @JoinColumn(name = "START_HOUR")
    private HourDayRef startHour;

    @ManyToOne
    @JoinColumn(name = "END_HOUR")
    private HourDayRef endHour;


}
