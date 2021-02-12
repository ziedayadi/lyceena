package com.zka.lyceena.dao;

import com.zka.lyceena.entities.classes.ClassMaterialSession;
import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.HourDayRef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassMaterialSessionJpaRepository extends JpaRepository<ClassMaterialSession, Integer> {

    @Query("Select s from ClassMaterialSession s " +
            "where s.clazz.id = :classId")
    List<ClassMaterialSession> findByClassId(@Param("classId") Long classId);

    @Query("Select s from ClassMaterialSession s " +
            "where s.teacher.id = :teacherId")
    List<ClassMaterialSession> findByTeacherId(@Param("teacherId") String teacherId);

    @Query("Delete from ClassMaterialSession s " +
            "where s.clazz.id = :classId")
    @Modifying
    void deleteByClassId(@Param("classId") Long classId);

    @Query("Select s from ClassMaterialSession s " +
            "where s.teacher.userName = :username " +
            "and s.dayOfWeek.id = :dayOfWeekId " +
            "and s.startHour.id = :hourOfDayId")
    Optional<ClassMaterialSession> findByTeacherUsernameDayStartHour(@Param("username") String userName,
                                                                     @Param("dayOfWeekId")  Integer dayOfWeekId,
                                                                     @Param("hourOfDayId") Integer hourOfDayId);
}
