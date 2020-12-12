package com.zka.lyceena.dao;

import com.zka.lyceena.entities.actors.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachersJpaRepository extends JpaRepository<Professor, String> {
}
