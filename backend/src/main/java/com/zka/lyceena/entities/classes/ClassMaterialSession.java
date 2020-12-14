package com.zka.lyceena.entities.classes;


import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.entities.material.Material;

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

    @Column(name = "DAY_OF_WEEK")
    @Enumerated(value = EnumType.STRING)
    private DayOfWeek dayOfWeek;

    @Column(name = "START_HOUR")
    private Integer startHour;

    @Column(name = "END_HOUR")
    private Integer endHour;


}
