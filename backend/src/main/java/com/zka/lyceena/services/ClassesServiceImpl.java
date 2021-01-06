package com.zka.lyceena.services;

import com.zka.lyceena.dao.ClassLevelRefJpaRepository;
import com.zka.lyceena.dao.ClassesJpaRepository;
import com.zka.lyceena.dto.ClassDto;
import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.entities.ref.ClassLevelRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesJpaRepository classesJpaRepository;

    @Autowired
    private ClassLevelRefJpaRepository classLevelRefJpaRepository;

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
}
