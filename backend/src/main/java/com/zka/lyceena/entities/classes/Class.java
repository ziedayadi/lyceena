package com.zka.lyceena.entities.classes;

import com.zka.lyceena.entities.ref.ClassLevelRef;
import com.zka.lyceena.entities.ref.ClassYear;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "CLASS")
@Data
public class Class {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne
    @JoinColumn(name = "LEVEL_ID")
    private ClassLevelRef level;

    @ManyToOne
    @JoinColumn(name = "CLASS_YEAR", nullable = false)
    private ClassYear classYear;
}
