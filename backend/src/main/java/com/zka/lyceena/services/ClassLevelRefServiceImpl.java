package com.zka.lyceena.services;

import com.zka.lyceena.dao.ClassLevelRefJpaRepository;
import com.zka.lyceena.dto.ClassLevelRefDto;
import com.zka.lyceena.entities.ref.ClassLevelRef;
import com.zka.lyceena.mappers.ClassLevelRefMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassLevelRefServiceImpl implements ClassLevelRefService {

    @Autowired
    private ClassLevelRefJpaRepository classLevelRefJpaRepository;

    @Override
    public List<ClassLevelRefDto> findAll() {
        return this.classLevelRefJpaRepository
                .findAll(Sort.by(Sort.Direction.ASC, "name"))
                .stream().map(ClassLevelRefMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public void save(ClassLevelRefDto dto) {
        ClassLevelRef entity = this.classLevelRefJpaRepository.findById(dto.getId()).orElse(new ClassLevelRef());
        entity.setName(dto.getName());
        this.classLevelRefJpaRepository.save(entity);
    }

    @Override
    public void deleteById(Integer id) {
        this.classLevelRefJpaRepository.deleteById(id);
    }
}
