package com.zka.lyceena.services;

import com.zka.lyceena.dao.*;
import com.zka.lyceena.dto.ClassDto;
import com.zka.lyceena.dto.TeacherDto;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.entities.ref.ClassLevelRef;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesJpaRepository classesJpaRepository;

    @Autowired
    private StudentsJpaRepository studentsJpaRepository;

    @Autowired
    private ClassLevelRefJpaRepository classLevelRefJpaRepository;

    @Autowired
    private TeachersJpaRepository teachersJpaRepository;

    @Autowired
    private ClassMaterialSessionJpaRepository classMaterialSessionJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Class> findAll() {
        return this.classesJpaRepository.findAll();
    }

    @Override
    public void save(ClassDto dto) {
        Class classEntity = this.classesJpaRepository.findById(dto.getId()).orElse(new Class());
        classEntity.setName(dto.getName());
        ClassLevelRef classLevelRef = this.classLevelRefJpaRepository.findById(dto.getLevelId()).get();
        classEntity.setLevel(classLevelRef);
        this.classesJpaRepository.save(classEntity);
    }

    @Override
    public void deleteById(Long id) {
        this.classesJpaRepository.deleteById(id);
    }

    @Override
    public Class findOne(Long id) {
        return this.classesJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> findStudentsByClassId(Long id) {
       return this.studentsJpaRepository.findByClassId(id);
    }

    @Override
    public List<TeacherDto> findTeachersByClassId(Long id) {
        List<Teacher> teachers =  this.teachersJpaRepository.findAll();
        return teachers.
                stream().
                filter(t-> t.getClasses().stream().filter(c -> c.getId() == id).count() > 0).
                map(t -> modelMapper.map(t, TeacherDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ClassMaterialSessionDto> findSessionsByClassId(Long id) {
        return this.classMaterialSessionJpaRepository.findByClassId(id)
                .stream()
                .map(s -> modelMapper.map(s, ClassMaterialSessionDto.class))
                .collect(Collectors.toList());
    }
}
