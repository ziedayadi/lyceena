package com.zka.lyceena.services;

import com.zka.lyceena.dao.StudentsJpaRepository;
import com.zka.lyceena.entities.actors.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentsServiceImpl implements StudentsService  {

    @Autowired
    private StudentsJpaRepository studentsJpaRepository;


    @Override
    public List<Student> findAll() {
        return this.studentsJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName", "lastName"));
    }
}
