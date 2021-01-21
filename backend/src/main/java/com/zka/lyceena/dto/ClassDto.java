package com.zka.lyceena.dto;

import com.zka.lyceena.entities.ref.ClassYear;
import lombok.Data;

@Data
public class ClassDto {
    private Long id;
    private String name;
    private Integer levelId;
    private ClassYear classYear;
}
