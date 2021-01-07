package com.zka.lyceena.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class MaterialDto {
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
}
