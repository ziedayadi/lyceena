package com.zka.lyceena.services;

import com.zka.lyceena.dto.TeacherDto;
import com.zka.lyceena.entities.actors.Teacher;

import java.util.List;

public interface TeachersService  {
        List<TeacherDto> findAll();
        Teacher save(TeacherDto teacher);
        void delete(String teacherId);
}
