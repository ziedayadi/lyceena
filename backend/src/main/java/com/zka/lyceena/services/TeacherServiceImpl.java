package com.zka.lyceena.services;

import com.zka.lyceena.constants.CacheNames;
import com.zka.lyceena.dao.ClassMaterialSessionJpaRepository;
import com.zka.lyceena.dao.ClassesJpaRepository;
import com.zka.lyceena.dao.MaterialRefJpaRepository;
import com.zka.lyceena.dao.TeachersJpaRepository;
import com.zka.lyceena.dto.ClassMaterialSessionDto;
import com.zka.lyceena.dto.TeacherDto;
import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.security.UserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeachersService {

    @Autowired
    private TeachersJpaRepository  teachersJpaRepository;

    @Autowired
    private MaterialRefJpaRepository materialRefJpaRepository;

    @Autowired
    private UserDetailsProvider userDetailsProvider;

    @Autowired
    private ClassMaterialSessionJpaRepository classMaterialSessionJpaRepository;

    @Autowired
    private ClassesJpaRepository classesJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Cacheable(CacheNames.TEACHERS)
    @Override
    public List<TeacherDto> findAll() {
        return this.teachersJpaRepository
                .findAll(Sort.by(Sort.Direction.ASC, "firstName","lastName"))
                .stream()
                .map(t->modelMapper.map(t,TeacherDto.class))
                .collect(Collectors.toList());
    }

    @CacheEvict(value=CacheNames.TEACHERS, allEntries=true)
    @Override
    public Teacher save(TeacherDto dto) {
        Teacher entity = this.teachersJpaRepository.findById(dto.getId()).orElse(new Teacher());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmailAdress(dto.getEmailAdress());
        entity.setStatus(dto.getStatus());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setUserName(dto.getUserName());
        entity.setMaterial(this.materialRefJpaRepository.findById(dto.getMaterialId()).orElse(null));

        return this.teachersJpaRepository.save(entity);
    }

    @CacheEvict(value=CacheNames.TEACHERS, allEntries=true)
    @Override
    public void delete(String teacherId) {
        this.teachersJpaRepository.deleteById(teacherId);
    }

    @Override
    public List getTimeSheet() {
        UserDetails userDetails = this.userDetailsProvider.getCurrentUserDetails();
        Optional<Teacher> optionalTeacher = this.teachersJpaRepository.findByUserName(userDetails.getUserName());

        if(optionalTeacher.isPresent()){
            return this.classMaterialSessionJpaRepository.findByTeacherId(optionalTeacher.get().getId())
                    .stream()
                    .map(s -> modelMapper.map(s, ClassMaterialSessionDto.class))
                    .sorted(Comparator.comparing(s -> s.getDayOfWeek().getId())
                    )
                    .collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }

    }

    @Transactional
    @Override
    public void replaceTeacherForClassMaterial(Map<String, Object> params) {
        Long classId = Long.parseLong(params.get("classId").toString());
        Object oldTeacherId  = params.get("oldTeacherId");
        Object newTeacherId  = params.get("newTeacherId");
        Class aClass = this.classesJpaRepository.findById(classId).orElseThrow();

        if(oldTeacherId != null && !oldTeacherId.equals(""))
        {
            Teacher oldTeacher = this.teachersJpaRepository.findById((String) oldTeacherId).orElseThrow();
            Class OldTeachersCLass =  oldTeacher.getClasses().stream().filter(c -> c.getId().equals(classId)).findAny().orElseThrow();
            oldTeacher.getClasses().remove(OldTeachersCLass);
            this.teachersJpaRepository.save(oldTeacher);
        }
        Teacher newTeacher = this.teachersJpaRepository.findById((String)newTeacherId).orElseThrow();
        newTeacher.getClasses().add(aClass);
        this.teachersJpaRepository.save(newTeacher);
    }
}
