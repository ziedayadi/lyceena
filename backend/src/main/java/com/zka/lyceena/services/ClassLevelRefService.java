package com.zka.lyceena.services;

import com.zka.lyceena.dto.ClassLevelRefDto;

import java.util.List;


public interface ClassLevelRefService {
    List<ClassLevelRefDto> findAll();

    void save(ClassLevelRefDto dto);

    void deleteById(Integer id);
}
