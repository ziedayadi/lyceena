package com.zka.lyceena.services;

import com.zka.lyceena.entities.material.Material;

import java.util.List;

public interface MaterialRefService {

    List<Material> findAll();
    void save(Material material);
    void deleteById(String id);

}
