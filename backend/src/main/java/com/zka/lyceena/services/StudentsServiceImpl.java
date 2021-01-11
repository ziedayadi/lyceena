package com.zka.lyceena.services;

import com.zka.lyceena.dao.ClassesJpaRepository;
import com.zka.lyceena.dao.ParentsJpaRepository;
import com.zka.lyceena.dao.StudentsJpaRepository;
import com.zka.lyceena.dto.StudentDto;
import com.zka.lyceena.entities.actors.Parent;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.classes.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentsJpaRepository studentsJpaRepository;

    @Autowired
    private ParentsJpaRepository parentsJpaRepository;

    @Autowired
    private ClassesJpaRepository classesJpaRepository;

    @Override
    public List<Student> findAll() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return this.studentsJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName", "lastName"));
    }

    public void save(StudentDto dto) {

        Student entity = this.studentsJpaRepository.findById(dto.getId()).orElse(new Student());

        entity.setSex(dto.getSex());
        entity.setStatus(dto.getStatus());
        entity.setEmailAdress(dto.getEmailAdress());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setBirthDate(dto.getBirthDate());

        Parent parent = this.parentsJpaRepository.findById(dto.getParentId()).get();
        Class aClass = this.classesJpaRepository.findById(dto.getClassId()).get();
        entity.setParent(parent);
        entity.setAClass(aClass);

        this.studentsJpaRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        this.studentsJpaRepository.deleteById(id);
    }
}
