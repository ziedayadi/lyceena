package com.zka.lyceena.entities.actors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PARENT")
public class Parent extends User {

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
}
