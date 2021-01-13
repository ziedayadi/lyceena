package com.zka.lyceena.dao;

import com.zka.lyceena.entities.actors.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TeachersJpaRepository extends JpaRepository<Teacher, String> {

    Optional<Teacher> findByUserName(String userName);
}
