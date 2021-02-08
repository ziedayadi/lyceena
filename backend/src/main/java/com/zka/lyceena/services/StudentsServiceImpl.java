package com.zka.lyceena.services;

import com.zka.lyceena.constants.CacheNames;
import com.zka.lyceena.dao.ClassesJpaRepository;
import com.zka.lyceena.dao.ParentsJpaRepository;
import com.zka.lyceena.dao.StudentsJpaRepository;
import com.zka.lyceena.dto.StudentDto;
import com.zka.lyceena.entities.actors.Parent;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.classes.Class;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private UserDetailsProvider userDetailsProvider;

    @Cacheable(CacheNames.STUDENTS)
    @Override
    public List<Student> findAll() {
        return this.studentsJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName", "lastName"));
    }

    @CacheEvict(value=CacheNames.STUDENTS, allEntries=true)
    @Override
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
    @CacheEvict(value=CacheNames.STUDENTS, allEntries=true)
    public void deleteById(String id) {
        this.studentsJpaRepository.deleteById(id);
    }

    @Override
    public List<Student> findByParentId(String parentId) {
        return this.studentsJpaRepository.findByParentId(parentId);
    }

    @Override
    public Student findOne(String id) {
        return this.studentsJpaRepository.findById(id).orElseThrow();
    }

    @Override
    public Student findOneByUsername(String username) {
        return this.studentsJpaRepository.findByUserName(username).orElseThrow();
    }
}
