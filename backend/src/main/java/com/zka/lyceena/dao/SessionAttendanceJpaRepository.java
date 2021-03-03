package com.zka.lyceena.dao;

import com.zka.lyceena.entities.attendance.SessionAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SessionAttendanceJpaRepository extends JpaRepository<SessionAttendance, Long> {


    @Query("Select s from SessionAttendance s " +
            "where s.classMaterialSession.id = :sessionId " +
            "and s.date = :date ")
    Optional<SessionAttendance> findByClassMaterialSessionIdAndDate(@Param("sessionId") Integer id,
                                                                    @Param("date") Date date);

    @Query("Select s from SessionAttendance s " +
            "where s.classMaterialSession.teacher.userName = :teacherUserName " +
            "order by s.date desc")
    List<SessionAttendance> findByTeacherUsername(String teacherUserName);

    @Query("Select s from SessionAttendance s " +
            "where s.classMaterialSession.clazz.id = :classId " +
            "order by s.date desc")
    List<SessionAttendance> findByClassId(@Param("classId") Long id);
}
