package com.zka.lyceena.entities.ref;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "HOUR_DAY_REF")
public class HourDayRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE")
    private String code;

}
