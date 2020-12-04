package com.zka.lyceena.entities.actors;


import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.entities.material.Material;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PROFESSOR")
@Data
public class Professor extends User {

    @ManyToOne
    @JoinColumn(name = "MATERIAL_ID")
    private Material material;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
            name = "PROFESSORS_CLASSES",
            joinColumns = { @JoinColumn(name = "PROFESSOR_ID") },
            inverseJoinColumns = { @JoinColumn(name = "CLASS_ID") }
    )
    private List<Class> classes;
}
