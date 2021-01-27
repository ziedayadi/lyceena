package com.zka.lyceena.entities.actors;


import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.entities.ref.Sex;
import lombok.Data;

import javax.persistence.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "STUDENT")
@Data
public class Student extends User{


    @Column(name = "REGISTRATION_NUMBER", unique = true)
    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name = "CLASS_ID")
    private Class aClass;

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    private Parent parent;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "SEX")
    @Enumerated(EnumType.STRING)
    private Sex sex;

    public Student(){
        super();

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        this.registrationNumber = "LY"+generatedString.toUpperCase();
    }

}
