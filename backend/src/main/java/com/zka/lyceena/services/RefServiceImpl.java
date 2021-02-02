package com.zka.lyceena.services;

import com.zka.lyceena.constants.CacheNames;
import com.zka.lyceena.dao.*;
import com.zka.lyceena.entities.ref.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class RefServiceImpl implements RefService {

    @Autowired
    private ClassYearJpaRepository  classYearJpaRepository;

    @Autowired
    private DayWeekRefJpaRepository dayWeekRefJpaRepository;

    @Autowired
    private HourDayRefJpaRepository hourDayRefJpaRepository;

    @Autowired
    private EmployeeRefTypeJpaRepository employeeRefTypeJpaRepository;

    @Autowired
    private GlobalRefJpaRepository  globalRefJpaRepository;

    @Override
    public ClassYear getCurrentClassYear() {
        Integer currentSystemYear = Calendar.getInstance().get(Calendar.YEAR);
        Integer currentMonthNumber = Calendar.getInstance().get(Calendar.MONTH);

        if(currentMonthNumber < 9){
            return this.classYearJpaRepository.findAll().stream().filter(cy -> cy.getEndYear().equals(currentSystemYear)).findAny().orElseThrow();
        } else {
            return this.classYearJpaRepository.findAll().stream().filter(cy -> cy.getStartYear().equals(currentSystemYear)).findAny().orElseThrow();
        }
    }

    @Cacheable(CacheNames.YEARS)
    @Override
    public List<ClassYear> findAllClassYears() {
        return this.classYearJpaRepository.findAll(Sort.by(Sort.Direction.ASC,"startDate"));
    }

    @Cacheable(CacheNames.DAYS)
    @Override
    public List<DayWeekRef> findAllDays() {
        return this.dayWeekRefJpaRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    @Cacheable(CacheNames.HOURS)
    @Override
    public List<HourDayRef> findAllHours() {
        return this.hourDayRefJpaRepository.findAll(Sort.by(Sort.Direction.ASC,"id"));
    }

    @Cacheable(CacheNames.EMPLOYEES_TYPES)
    @Override
    public List<EmployeeTypeRef> findAllEmployeeTypeRef() {
        return this.employeeRefTypeJpaRepository.findAll();
    }

    @Cacheable(CacheNames.GLOBAL_REF)
    @Override
    public List<GlobalRef> findAllGlobalRefData() {
        return globalRefJpaRepository.findAll();
    }

    @Override
    public Optional<DayWeekRef> findDayWeekRefById(Integer id) {
        return this.findAllDays().stream().filter(d-> d.getId().equals(id)).findAny();
    }

    @Override
    public Optional<HourDayRef> findHourDayRefById(Integer id) {
        return this.findAllHours().stream().filter(d-> d.getId().equals(id)).findAny();
    }
}
