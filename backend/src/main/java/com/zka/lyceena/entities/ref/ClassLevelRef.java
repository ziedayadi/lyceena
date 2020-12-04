package com.zka.lyceena.entities.ref;

import com.zka.lyceena.entities.material.Material;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CLASS_LEVEL_REF")
@Data
public class ClassLevelRef {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "CLASS_LEVEL_REF_MATERIALS",
            joinColumns = { @JoinColumn(name = "CLASS_LEVEL_REF_ID") },
            inverseJoinColumns = { @JoinColumn(name = "MATERIAL_ID") }
    )
    private List<Material> materials;

}
