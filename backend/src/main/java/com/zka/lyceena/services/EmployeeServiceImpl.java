package com.zka.lyceena.services;

import com.zka.lyceena.dao.EmployeeJpaRepository;
import com.zka.lyceena.dao.EmployeeRefTypeJpaRepository;
import com.zka.lyceena.dto.EmployeeDto;
import com.zka.lyceena.entities.actors.Employee;
import com.zka.lyceena.entities.ref.EmployeeTypeRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeesService {

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    private EmployeeRefTypeJpaRepository employeeRefTypeJpaRepository;

    @Override
    public List<Employee> findAll() {
        return this.employeeJpaRepository.findAll(Sort.by(Sort.Direction.DESC, "firstName", "lastName"));
    }

    @Override
    public void save(EmployeeDto employeeDto) {
        Employee entity = this.employeeJpaRepository.findById(employeeDto.getId()).orElse(new Employee());

        entity.setFirstName(employeeDto.getFirstName());
        entity.setLastName(employeeDto.getLastName());
        entity.setStatus(employeeDto.getStatus());
        entity.setEmailAdress(employeeDto.getEmailAdress());

        EmployeeTypeRef type = this.employeeRefTypeJpaRepository.findById(employeeDto.getTypeId()).get();
        entity.setType(type);

        this.employeeJpaRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        this.employeeJpaRepository.deleteById(id);
    }
}
