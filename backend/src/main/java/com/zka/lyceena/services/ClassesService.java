package com.zka.lyceena.services;

import com.zka.lyceena.dto.ClassDto;
import com.zka.lyceena.dto.TeacherDto;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.entities.classes.Class;

import java.util.List;

public interface ClassesService {

    List<Class> findAll();

    void save(ClassDto dto);

    void deleteById(Long id);

    Class findOne(Long id);

    List<Student> findStudentsByClassId(Long id);

    List<TeacherDto> findTeachersByClassId(Long id);
}
