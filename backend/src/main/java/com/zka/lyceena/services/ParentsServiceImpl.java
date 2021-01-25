package com.zka.lyceena.services;

import com.zka.lyceena.constants.CacheNames;
import com.zka.lyceena.dao.ParentsJpaRepository;
import com.zka.lyceena.dto.ParentDto;
import com.zka.lyceena.entities.actors.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParentsServiceImpl implements ParentsService {

    @Autowired
    private ParentsJpaRepository parentsJpaRepository;

    @Cacheable(CacheNames.PARENTS)
    @Override
    public List<Parent> findAll() {
        return this.parentsJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName", "lastName"));
    }

    @CacheEvict(CacheNames.PARENTS)
    @Override
    public void save(ParentDto parenDto) {
        Parent parent = this.parentsJpaRepository.findById(parenDto.getId()).orElse(new Parent());
        parent.setFirstName(parenDto.getFirstName());
        parent.setLastName(parenDto.getLastName());
        parent.setStatus(parenDto.getStatus());
        parent.setPhoneNumber(parenDto.getPhoneNumber());
        parent.setEmailAdress(parenDto.getEmailAdress());
        this.parentsJpaRepository.save(parent);
    }

    @CacheEvict(CacheNames.PARENTS)
    @Override
    public void deleteById(String parentId) {
        this.parentsJpaRepository.deleteById(parentId);
    }
}
