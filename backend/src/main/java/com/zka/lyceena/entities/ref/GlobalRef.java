package com.zka.lyceena.entities.ref;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "GLOBAL_REF")
public class GlobalRef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE", nullable = false)
    private String code;

    @Column(name = "VALUE")
    private String value;

}
