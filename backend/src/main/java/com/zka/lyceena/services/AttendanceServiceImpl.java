package com.zka.lyceena.services;

import com.zka.lyceena.dao.ClassMaterialSessionJpaRepository;
import com.zka.lyceena.dao.SessionAttendanceJpaRepository;
import com.zka.lyceena.dto.attendance.SessionAttendanceDto;
import com.zka.lyceena.entities.attendance.SessionAttendance;
import com.zka.lyceena.entities.classes.ClassMaterialSession;
import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.HourDayRef;
import com.zka.lyceena.security.UserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

    @Autowired
    private UserDetailsProvider userDetailsProvider;

    @Autowired
    private SessionAttendanceJpaRepository sessionAttendanceJpaRepository;

    @Autowired
    private RefService refService;

    @Autowired
    private ClassMaterialSessionJpaRepository classMaterialSessionJpaRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public SessionAttendanceDto getCurrentSessionForTeacher() {

        UserDetails teacher = this.userDetailsProvider.getCurrentUserDetails();
        DayWeekRef dayOfWeek = this.refService.getCurrentDayOfWeek();
        HourDayRef hourOfDay = this.refService.getCurrentHourOfDay();

        Optional<ClassMaterialSession> session = this.classMaterialSessionJpaRepository.findByTeacherUsernameDayStartHour(teacher.getUserName(), dayOfWeek.getId(), hourOfDay.getId());
        if(session.isPresent()){
            // Check if the session attendance is present
            Optional<SessionAttendance> sessionAttendance = this.sessionAttendanceJpaRepository.findByClassMaterialSessionIdAndDate(session.get().getId(), new Date());
            if(sessionAttendance.isPresent()){
                // Session attendance is present
               return this.modelMapper.map(sessionAttendance, SessionAttendanceDto.class);
            } else {
                // Session attendance is not present => Create new one
                SessionAttendance newSessionAttendance = new SessionAttendance();
                newSessionAttendance.setClassMaterialSession(session.get());
                newSessionAttendance.setDate(new Date());
                this.sessionAttendanceJpaRepository.save(newSessionAttendance);


                return null;
            }
        } else {
            return null;
        }
    }
}
