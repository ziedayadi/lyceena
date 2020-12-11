package com.zka.lyceena.services;


import com.zka.lyceena.dao.MaterialRefJpaRepository;
import com.zka.lyceena.entities.material.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialRefServiceImpl implements MaterialRefService{

    @Autowired
    private MaterialRefJpaRepository materialRefJpaRepository;

    @Override
    public List<Material> findAll() {
        return this.materialRefJpaRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }
}
