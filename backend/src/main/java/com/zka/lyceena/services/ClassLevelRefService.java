package com.zka.lyceena.services;

import com.zka.lyceena.dto.ClassLevelRefDto;
import com.zka.lyceena.dto.LevelMaterialNumberOfHoursDto;
import com.zka.lyceena.dto.MaterialDto;
import com.zka.lyceena.dto.SaveMaterialToClassLevelDto;

import java.util.List;


public interface ClassLevelRefService {
    List<ClassLevelRefDto> findAll();

    void save(ClassLevelRefDto dto);

    void deleteById(Integer id);

    ClassLevelRefDto findById(Integer id);

    List<LevelMaterialNumberOfHoursDto> findMaterialsByClassLevelId(Integer id);

    void deleteClassLevelMaterialByClassIdMaterialId(Integer levelId, String materialId);

    /**
     * Add Materials to Class Level
     * @param levelId
     * @param saveMaterialToClassLevelDto
     */
    void addMaterialToClassLevel(Integer levelId, SaveMaterialToClassLevelDto saveMaterialToClassLevelDto);
}
