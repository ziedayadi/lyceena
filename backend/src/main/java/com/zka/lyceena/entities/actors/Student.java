package com.zka.lyceena.entities.actors;


import com.zka.lyceena.entities.classes.Class;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STUDENT")
@Data
public class Student extends User{

    @ManyToOne
    @JoinColumn(name = "CLASS_ID")
    private Class aClass;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

}
