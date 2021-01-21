package com.zka.lyceena.services;

import com.zka.lyceena.entities.ref.ClassYear;

import java.util.List;

public interface RefService {

    ClassYear getCurrentClassYear();
    List<ClassYear> findAllClassYears();
}
