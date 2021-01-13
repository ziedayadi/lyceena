package com.zka.lyceena.services;

import com.zka.lyceena.dao.ClassMaterialSessionJpaRepository;
import com.zka.lyceena.dao.MaterialRefJpaRepository;
import com.zka.lyceena.dao.TeachersJpaRepository;
import com.zka.lyceena.dto.ClassMaterialSessionDto;
import com.zka.lyceena.dto.TeacherDto;
import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.security.UserDetails;
import com.zka.lyceena.security.UserDetailsProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
    private ModelMapper modelMapper;

    @Override
    public List<TeacherDto> findAll() {
        return this.teachersJpaRepository
                .findAll(Sort.by(Sort.Direction.ASC, "firstName","lastName"))
                .stream()
                .map(t->modelMapper.map(t,TeacherDto.class))
                .collect(Collectors.toList());
    }

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

    @Override
    public void delete(String teacherId) {
        this.teachersJpaRepository.deleteById(teacherId);
    }

    @Override
    public List<ClassMaterialSessionDto> getTimeSheet() {
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
}
