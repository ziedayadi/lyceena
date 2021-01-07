package com.zka.lyceena.entities.ref;

import com.zka.lyceena.entities.material.Material;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "LEVEL_MATERIAL_HOUR_NUMBER")
public class LevelMaterialNumberOfHours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "LEVEL_ID")
    private ClassLevelRef classLevelRef;

    @ManyToOne
    @JoinColumn(name = "MATERIAL_ID")
    private Material material;

    @Column(name = "HOURS_NUMBER_BY_WEEK")
    private Integer hourNumberPerWeek;

}
