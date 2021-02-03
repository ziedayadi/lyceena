package com.zka.lyceena.dto;

import com.zka.lyceena.entities.ref.Sex;
import lombok.Data;

import java.util.Date;

@Data
public class StudentDto extends UserDto {

    private String registrationNumber;
    private Long classId;
    private String parentId;
    private Date birthDate;
    private Sex sex;
    private ClassDto aClass;
}
