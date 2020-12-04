package com.zka.lyceena.entities.actors;


import com.zka.lyceena.entities.ref.UserStatus;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "USER")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class User implements Serializable {

    @Id
    @Column(name = "ID")
    private String id = UUID.randomUUID().toString();

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String emailAdress;

    @Column(name = "STATUS")
    @Enumerated(value = EnumType.ORDINAL)
    private UserStatus status;

}
