package com.zka.lyceena.mappers;

import com.zka.lyceena.dto.ClassLevelRefDto;
import com.zka.lyceena.entities.ref.ClassLevelRef;

public class ClassLevelRefMapper {

    public static final ClassLevelRefDto map(ClassLevelRef entity){
        ClassLevelRefDto dto = new ClassLevelRefDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}
