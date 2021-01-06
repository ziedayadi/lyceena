package com.zka.lyceena.dao;

import com.zka.lyceena.entities.ref.EmployeeTypeRef;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRefTypeJpaRepository extends JpaRepository<EmployeeTypeRef, Integer> {
}
