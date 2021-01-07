package com.zka.lyceena.entities.ref;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DAY_WEEK_REF")
public class DayWeekRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FR")
    private String fr;

    @Column(name = "EN")
    private String en;

    @Column(name = "AR")
    private String ar;
}
