package com.zka.lyceena.services;

import com.zka.lyceena.dao.ClassMaterialSessionJpaRepository;
import com.zka.lyceena.dao.ClassRoomJpaRepository;
import com.zka.lyceena.dto.ClassRoomDto;
import com.zka.lyceena.entities.classes.ClassMaterialSession;
import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.HourDayRef;
import com.zka.lyceena.entities.rooms.ClassRoom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassRoomsServiceImpl implements ClassRoomsService {

    @Autowired
    private ClassRoomJpaRepository classRoomJpaRepository;

    @Autowired
    private ClassMaterialSessionJpaRepository classMaterialSessionJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ClassRoomDto> findAll() {
        return this.classRoomJpaRepository.
                findAll()
                .stream()
                .map(c -> modelMapper.map(c, ClassRoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(ClassRoomDto dto) {
        ClassRoom classRoom = this.modelMapper.map(dto, ClassRoom.class);
        this.classRoomJpaRepository.save(classRoom);
    }

    @Override
    public List<ClassRoom> findForFreeHour(DayWeekRef day, HourDayRef hour) {
        List<ClassMaterialSession> session = this.classMaterialSessionJpaRepository.findAll()
                .stream()
                .filter(s -> s.getDayOfWeek() != null && s.getStartHour() != null)
                .collect(Collectors.toList());

        List<ClassRoom> busyClassRooms = session.stream().filter(s -> s.getDayOfWeek().getId().equals(day.getId()) && s.getStartHour().getId().equals(hour.getId()))
                .map(ClassMaterialSession::getClassRoom)
                .collect(Collectors.toList());

        return this.classRoomJpaRepository.findByIdNot(busyClassRooms.stream().map(ClassRoom::getId).collect(Collectors.toSet()));

    }

    @Override
    public void deleteById(Integer id) {
        this.classRoomJpaRepository.deleteById(id);
    }
}
