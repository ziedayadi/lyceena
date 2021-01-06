package com.zka.lyceena.services;

import com.zka.lyceena.dto.EmployeeDto;
import com.zka.lyceena.entities.actors.Employee;
import com.zka.lyceena.entities.material.Material;

import java.util.List;

public interface EmployeesService {
    List<Employee> findAll();
    void save(EmployeeDto employeeDto);
    void deleteById(String id);
}
