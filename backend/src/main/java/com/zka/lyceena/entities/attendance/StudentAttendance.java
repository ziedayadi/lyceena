package com.zka.lyceena.entities.attendance;


import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.ref.StudentAttendanceValue;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STUDENT_ATTENDANCE")
public class StudentAttendance {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "SESSION_ATTENDANCE_ID")
    private SessionAttendance sessionAttendance;

    @Column(name = "PRESENCE")
    @Enumerated(EnumType.STRING)
    private StudentAttendanceValue presence;

}
