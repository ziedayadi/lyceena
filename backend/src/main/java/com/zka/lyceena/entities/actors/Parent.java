package com.zka.lyceena.entities.actors;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PARENT")
@Data
public class Parent extends User {

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
}
