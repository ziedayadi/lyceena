package com.zka.lyceena.dao;

import com.zka.lyceena.entities.actors.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeachersJpaRepository extends JpaRepository<Teacher, String> {
}
