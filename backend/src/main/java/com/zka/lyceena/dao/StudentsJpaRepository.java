package com.zka.lyceena.dao;

import com.zka.lyceena.entities.actors.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface StudentsJpaRepository extends JpaRepository<Student, String> {
}
