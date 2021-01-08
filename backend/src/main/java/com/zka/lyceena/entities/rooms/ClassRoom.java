package com.zka.lyceena.entities.rooms;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CLASS_ROOM")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "CAPACITY")
    private Integer capacity;

}
