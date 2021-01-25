package com.zka.lyceena.services;

import com.zka.lyceena.constants.CacheNames;
import com.zka.lyceena.dao.ClassYearJpaRepository;
import com.zka.lyceena.entities.ref.ClassYear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class RefServiceImpl implements RefService {

    @Autowired
    private ClassYearJpaRepository  classYearJpaRepository;

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
}
