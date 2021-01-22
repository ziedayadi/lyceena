package com.zka.lyceena.dao;

import com.zka.lyceena.entities.classes.ClassMaterialSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;

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
}
