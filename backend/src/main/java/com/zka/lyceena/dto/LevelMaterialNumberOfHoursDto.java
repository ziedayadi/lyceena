package com.zka.lyceena.dto;

import lombok.Data;

@Data
public class LevelMaterialNumberOfHoursDto {

    private Integer id;
    private ClassLevelRefDto classLevelRef;
    private MaterialDto material;
    private Integer hourNumberPerWeek;
}
