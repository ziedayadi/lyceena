package com.zka.lyceena.services;

import com.zka.lyceena.dao.ClassRoomJpaRepository;
import com.zka.lyceena.dto.ClassRoomDto;
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
    private ModelMapper modelMapper;

    @Override
    public List<ClassRoomDto> findAll() {
        return this.classRoomJpaRepository.
                findAll()
                .stream()
                .map(c->modelMapper.map(c, ClassRoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void save(ClassRoomDto dto) {
        ClassRoom classRoom = this.modelMapper.map(dto, ClassRoom.class);
        this.classRoomJpaRepository.save(classRoom);
    }

    @Override
    public void deleteById(Integer id) {
        this.classRoomJpaRepository.deleteById(id);
    }
}
