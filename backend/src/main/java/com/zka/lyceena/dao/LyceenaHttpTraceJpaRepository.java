package com.zka.lyceena.dao;

import com.zka.lyceena.entities.trace.LyceenaHttpTrace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LyceenaHttpTraceJpaRepository extends JpaRepository<LyceenaHttpTrace, String> {
}
