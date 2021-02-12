package com.zka.lyceena.entities.attendance;

import com.zka.lyceena.entities.classes.ClassMaterialSession;
import com.zka.lyceena.entities.ref.SessionAttendanceStatusValue;
import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@Table(name = "SESSION_ATTENDANCE")
public class SessionAttendance {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "SESSION_ID")
    private ClassMaterialSession classMaterialSession;

    @Column(name = "STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    private SessionAttendanceStatusValue status;


}
