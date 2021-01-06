package com.zka.lyceena.dao;

import com.zka.lyceena.entities.actors.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeJpaRepository extends JpaRepository<Employee, String> {
}
