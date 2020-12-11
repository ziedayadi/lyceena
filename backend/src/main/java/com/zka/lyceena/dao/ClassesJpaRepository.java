package com.zka.lyceena.dao;

import com.zka.lyceena.entities.classes.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassesJpaRepository extends JpaRepository<Class, Long> {
}
