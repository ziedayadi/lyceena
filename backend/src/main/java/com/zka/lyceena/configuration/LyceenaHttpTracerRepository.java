package com.zka.lyceena.configuration;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LyceenaHttpTracerRepository extends InMemoryHttpTraceRepository {

    @Override
    public List<HttpTrace> findAll() {
        return super.findAll();
    }

    @Override
    public void add(HttpTrace trace) {
        if (trace.getRequest().getUri().getPath().contains("/actuator")
                || trace.getRequest().getUri().getPath().contains("/menus")
        ) {
            return;
        }
        super.add(trace);
    }
}
