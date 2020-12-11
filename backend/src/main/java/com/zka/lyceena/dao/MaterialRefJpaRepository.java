package com.zka.lyceena.dao;

import com.zka.lyceena.entities.material.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MaterialRefJpaRepository extends JpaRepository<Material,String> {
}
