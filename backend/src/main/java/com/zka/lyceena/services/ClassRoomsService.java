package com.zka.lyceena.services;

import com.zka.lyceena.dto.ClassRoomDto;
import com.zka.lyceena.entities.rooms.ClassRoom;

import java.util.List;

public interface ClassRoomsService {

    List<ClassRoomDto> findAll();

    void save(ClassRoomDto dto);

    void deleteById(Integer id);

    ClassRoom findById(Integer id);

    List<ClassRoom> findForFreeHour(Integer dayId, Integer hourId);
}
