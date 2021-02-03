package com.zka.lyceena.services;

import com.zka.lyceena.dto.ClassMaterialSessionDto;
import com.zka.lyceena.dto.TeacherDto;
import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.entities.classes.Class;

import java.util.List;
import java.util.Map;

public interface TeachersService {
    List<TeacherDto> findAll();

    Teacher save(TeacherDto teacher);

    void delete(String teacherId);

    List<ClassMaterialSessionDto> getTimeSheet();

    void replaceTeacherForClassMaterial(Map<String, Object> params);

    Teacher findOne(String id);

    List<Class> findClassesByTeacherId(String teacherId);
}
