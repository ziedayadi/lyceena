package com.zka.lyceena.dao;

import com.zka.lyceena.entities.actors.Parent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentsJpaRepository extends JpaRepository<Parent, String> {
}
