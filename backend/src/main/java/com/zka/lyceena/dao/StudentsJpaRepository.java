package com.zka.lyceena.dao;

import com.zka.lyceena.entities.actors.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentsJpaRepository extends JpaRepository<Student, String> {

    @Query("select s from Student s " +
            "where s.aClass.id = :classId")
    List<Student> findByClassId(@Param("classId") Long classId);

    @Query("select s from Student s " +
            "where s.parent.id = :parentId " +
            "order by s.firstName, s.lastName")
    List<Student> findByParentId(@Param("parentId") String classId);


    Optional<Student> findByUserName(String username);
}
