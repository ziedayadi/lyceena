package com.zka.lyceena.services;

import com.zka.lyceena.dto.StudentDto;
import com.zka.lyceena.entities.actors.Student;

import java.util.List;

public interface StudentsService {

    List<Student> findAll();
    void save(StudentDto dto);
    void deleteById(String id);
    List<Student> findByParentId(String parentId);
    Student findOne(String id);
    Student findOneByUsername(String username);
}
