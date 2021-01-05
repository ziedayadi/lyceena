package com.zka.lyceena.services;

import com.zka.lyceena.dao.ParentsJpaRepository;
import com.zka.lyceena.dto.ParentDto;
import com.zka.lyceena.entities.actors.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentsServiceImpl implements ParentsService {

    @Autowired
    private ParentsJpaRepository parentsJpaRepository;

    @Override
    public List<Parent> findAll() {
        return this.parentsJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName", "lastName"));
    }

    @Override
    public void save(ParentDto parenDto) {
        Parent parent = new Parent();
        parent.setFirstName(parenDto.getFirstName());
        parent.setLastName(parenDto.getLastName());
        parent.setStatus(parenDto.getStatus());
        parent.setPhoneNumber(parenDto.getPhoneNumber());
        parent.setEmailAdress(parenDto.getEmailAdress());
        parent.setId(parenDto.getId());
        this.parentsJpaRepository.save(parent);
    }

    @Override
    public void deleteById(String parentId) {
        this.parentsJpaRepository.deleteById(parentId);
    }
}
