package com.zka.lyceena.entities.classes;

import com.zka.lyceena.entities.ref.ClassLevelRef;
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
}
