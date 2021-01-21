package com.zka.lyceena.entities.ref;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CLASS_YEAR_REF")
public class ClassYear {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "START_YEAR", nullable = false)
    private Integer startYear;

    @Column(name = "END_YEAR", nullable = false)
    private Integer endYear;
}
