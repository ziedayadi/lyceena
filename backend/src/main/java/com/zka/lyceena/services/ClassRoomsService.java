package com.zka.lyceena.services;

import com.zka.lyceena.dto.ClassRoomDto;

import java.util.List;

public interface ClassRoomsService {

    List<ClassRoomDto> findAll();

    void save(ClassRoomDto dto);

    void deleteById(Integer id);

}
