package com.zka.lyceena.configuration;

import com.zka.lyceena.dao.LyceenaHttpTraceJpaRepository;
import com.zka.lyceena.entities.trace.LyceenaHttpTrace;
import com.zka.lyceena.services.UserDetailsProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LyceenaHttpTracerRepository extends InMemoryHttpTraceRepository {

    @Autowired
    private LyceenaHttpTraceJpaRepository lyceenaHttpTraceJpaRepository;

    @Autowired
    private UserDetailsProvider userDetailsProvider;

    @Override
    public List<HttpTrace> findAll() {
        return super.findAll();
    }

    @Override
    public void add(HttpTrace trace) {
        if (trace.getRequest().getUri().getPath().contains("/actuator")
                || trace.getRequest().getUri().getPath().contains("/menus")
                || trace.getRequest().getMethod().equals(HttpMethod.GET.toString())
        ) {
            return;
        }
        LyceenaHttpTrace lyceenaHttpTrace = new LyceenaHttpTrace();
        lyceenaHttpTrace.setMethod(trace.getRequest().getMethod());
        lyceenaHttpTrace.setUsername(this.userDetailsProvider.getCurrentUserDetails().getUserName());
        lyceenaHttpTrace.setTimestamp(trace.getTimestamp());
        lyceenaHttpTrace.setResponseStatus(trace.getResponse().getStatus());
        lyceenaHttpTrace.setTimeTaken(trace.getTimeTaken());
        lyceenaHttpTrace.setUri(trace.getRequest().getUri().getPath());
        this.lyceenaHttpTraceJpaRepository.save(lyceenaHttpTrace);

        super.add(trace);
    }
}
