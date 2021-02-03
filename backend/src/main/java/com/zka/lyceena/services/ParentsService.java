package com.zka.lyceena.services;

import com.zka.lyceena.dto.ParentDto;
import com.zka.lyceena.entities.actors.Parent;

import java.util.List;

public interface ParentsService {

    List<Parent> findAll();
    void save(ParentDto parenDto);
    void deleteById(String parentId);
    Parent findOne(String parentId);

}
