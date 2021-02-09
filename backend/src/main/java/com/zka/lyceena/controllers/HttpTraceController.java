package com.zka.lyceena.controllers;


import com.zka.lyceena.dao.LyceenaHttpTraceJpaRepository;
import com.zka.lyceena.entities.trace.LyceenaHttpTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/httptrace")
public class HttpTraceController {

    @Autowired
    private LyceenaHttpTraceJpaRepository lyceenaHttpTraceJpaRepository;

    @GetMapping("/")
    public List<LyceenaHttpTrace> traces(){
        return lyceenaHttpTraceJpaRepository.findAll(Sort.by(Sort.Direction.DESC, "timestamp"));
    }
}
