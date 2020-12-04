package com.zka.lyceena.entities.actors;


import com.zka.lyceena.entities.ref.EmployeeTypeRef;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
@Data
public class Employee extends User{

    @ManyToOne
    @JoinColumn(name = "EMP_TYPE_REF_ID")
    private EmployeeTypeRef type;
}
