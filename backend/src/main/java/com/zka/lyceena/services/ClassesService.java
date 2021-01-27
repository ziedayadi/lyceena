package com.zka.lyceena.services;

import com.zka.lyceena.dto.*;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.classes.Class;

import java.util.List;

public interface ClassesService {

    List<Class> findAll();

    void save(ClassDto dto);

    void deleteById(Long id);

    Class findOne(Long id);

    List<Student> findStudentsByClassId(Long id);

    List<TeacherDto> findTeachersByClassId(Long id);

    List<ClassMaterialSessionDto> findSessionsByClassId(Long id);

    List<ClassMaterialSessionDto> createTimeSheetByClassId(Long id);

    List<DayHourDto> findFreeDateHourByClassIdTeacherId(Long classId, String teacherId);

    void updateSession(UpdateSessionDto updateSessionDto);
}
