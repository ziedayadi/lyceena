package com.zka.lyceena.dao;

import com.zka.lyceena.entities.attendance.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentAttendanceJpaRepository extends JpaRepository<StudentAttendance, Long> {

    @Query("Select s from StudentAttendance s " +
            "where s.sessionAttendance.id = :sessionAttendanceId")
    List<StudentAttendance> findBySessionAttendanceId(@Param("sessionAttendanceId") Long id);
}
