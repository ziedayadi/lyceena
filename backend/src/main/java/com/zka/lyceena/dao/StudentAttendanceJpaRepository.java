package com.zka.lyceena.dao;

import com.zka.lyceena.entities.attendance.StudentAttendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentAttendanceJpaRepository extends JpaRepository<StudentAttendance, Long> {

    @Query("Select s from StudentAttendance s " +
            "where s.sessionAttendance.id = :sessionAttendanceId " +
            "order by s.student.firstName asc, s.student.lastName asc")
    List<StudentAttendance> findBySessionAttendanceId(@Param("sessionAttendanceId") Long id);


    @Query("Select s from StudentAttendance s " +
            "where s.sessionAttendance.id = :sessionAttendanceId " +
            "and s.student.id = :studentId")
    Optional<StudentAttendance> findBySessionAttendanceIdAndStudentId(@Param("sessionAttendanceId")Long sessionAttendanceId,
                                                                      @Param("studentId") String studentId);

    @Query("Select s from StudentAttendance s " +
            "where s.student.id = :studentId " +
            "order by s.sessionAttendance.date desc")
    List<StudentAttendance> findByStudentId(@Param("studentId") String id);
}
