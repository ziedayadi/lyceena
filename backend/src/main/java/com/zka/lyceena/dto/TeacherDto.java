package com.zka.lyceena.dto;

import lombok.Data;

@Data
public class TeacherDto extends UserDto {
    private String materialId;
    private String phoneNumber;
}
