package com.zka.lyceena.services;

import com.zka.lyceena.dao.ClassMaterialSessionJpaRepository;
import com.zka.lyceena.dao.SessionAttendanceJpaRepository;
import com.zka.lyceena.dao.StudentAttendanceJpaRepository;
import com.zka.lyceena.dao.StudentsJpaRepository;
import com.zka.lyceena.dto.attendance.*;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.attendance.SessionAttendance;
import com.zka.lyceena.entities.attendance.StudentAttendance;
import com.zka.lyceena.entities.classes.ClassMaterialSession;
import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.HourDayRef;
import com.zka.lyceena.entities.ref.SessionAttendanceStatusValue;
import com.zka.lyceena.entities.ref.StudentAttendanceValue;
import com.zka.lyceena.security.UserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private StudentsJpaRepository studentsJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentAttendanceJpaRepository studentAttendanceJpaRepository;


    @Override
    public SessionAttendanceDto getCurrentSessionForTeacher() {

        UserDetails teacher = this.userDetailsProvider.getCurrentUserDetails();
        DayWeekRef dayOfWeek = this.refService.getCurrentDayOfWeek();
        HourDayRef hourOfDay = this.refService.getCurrentHourOfDay();

        Optional<ClassMaterialSession> session = this.classMaterialSessionJpaRepository.findByTeacherUsernameDayStartHour(teacher.getUserName(), dayOfWeek.getId(), hourOfDay.getId());
        if (session.isPresent()) {
            // Check if the session attendance is present
            Optional<SessionAttendance> sessionAttendance = this.sessionAttendanceJpaRepository.findByClassMaterialSessionIdAndDate(session.get().getId(), new Date());
            SessionAttendanceDto sessionAttendanceDto = null;
            if (sessionAttendance.isPresent()) {
                sessionAttendanceDto = this.prepareSessionAttendanceDto(sessionAttendance.get());
            } else {
                // Session attendance is not present => Create new one
                SessionAttendance newSessionAttendance = new SessionAttendance();
                newSessionAttendance.setStatus(SessionAttendanceStatusValue.NEW);
                newSessionAttendance.setClassMaterialSession(session.get());
                newSessionAttendance.setDate(new Date());
                this.sessionAttendanceJpaRepository.save(newSessionAttendance);

                List<Student> students = this.studentsJpaRepository.findByClassId(session.get().getClazz().getId());
                List<StudentAttendance> studentAttendances = students.stream().map(s -> {
                    StudentAttendance studentAttendance = new StudentAttendance();
                    studentAttendance.setStudent(s);
                    studentAttendance.setSessionAttendance(newSessionAttendance);
                    studentAttendance.setPresence(StudentAttendanceValue.NA);
                    return studentAttendance;
                }).collect(Collectors.toList());

                this.studentAttendanceJpaRepository.saveAll(studentAttendances);
                sessionAttendanceDto = this.modelMapper.map(newSessionAttendance, SessionAttendanceDto.class);
                sessionAttendanceDto.setStudents(studentAttendances.stream().map(s -> modelMapper.map(s, StudentAttendanceDto.class)).collect(Collectors.toList()));

            }
            return sessionAttendanceDto;
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public SessionAttendanceDto saveStudentAttendanceForSessionByTeacher(SaveStudentAttendanceDto saveStudentAttendance) {
        // 1. Find Student attendance for given attendance and student ID
        StudentAttendance studentAttendance = this.studentAttendanceJpaRepository.findBySessionAttendanceIdAndStudentId(saveStudentAttendance.getSessionAttendanceId(),
                saveStudentAttendance.getStudentId()).orElseThrow();
        //2. Set the new attendance
        studentAttendance.setPresence(saveStudentAttendance.getStudentAttendance());
        //3. Save
        this.studentAttendanceJpaRepository.save(studentAttendance);
        //4. Return the value
        return this.getCurrentSessionForTeacher();
    }

    @Transactional
    @Override
    public SessionAttendanceDto updateStatus(Long sessionAttendanceId, SessionAttendanceStatusValue status) {
        SessionAttendance session = this.sessionAttendanceJpaRepository.findById(sessionAttendanceId).orElseThrow();
        session.setStatus(status);
        this.sessionAttendanceJpaRepository.save(session);
        return this.prepareSessionAttendanceDto(session);
    }

    @Transactional
    @Override
    public SessionAttendanceDto saveSessionText(SaveSessionText saveSessionText) {
        SessionAttendance session = this.sessionAttendanceJpaRepository.findById(saveSessionText.getSessionAttendanceId()).orElseThrow();
        session.setSessionText(saveSessionText.getSessionText());
        this.sessionAttendanceJpaRepository.save(session);
        return this.prepareSessionAttendanceDto(session);
    }

    @Override
    public List<SessionAttendanceGlobalInformationDto> getSessionForTeacher() {
        UserDetails teacher = this.userDetailsProvider.getCurrentUserDetails();
        return sessionAttendanceJpaRepository.findByTeacherUsername(teacher.getUserName())
                .stream()
                .map(s-> this.modelMapper.map(s, SessionAttendanceGlobalInformationDto.class))
                .collect(Collectors.toList());
    }

    private SessionAttendanceDto prepareSessionAttendanceDto(SessionAttendance session){
        SessionAttendanceDto sessionAttendanceDto = this.modelMapper.map(session, SessionAttendanceDto.class);
        List<StudentAttendance> studentAttendances = this.studentAttendanceJpaRepository.findBySessionAttendanceId(session.getId());
        sessionAttendanceDto.setStudents(studentAttendances.stream().map(s -> modelMapper.map(s, StudentAttendanceDto.class)).collect(Collectors.toList()));
        return sessionAttendanceDto;
    }

    @Override
    public SessionAttendanceDto getSessionById(Long sessionId) {
        Optional<SessionAttendance> optionalSessionAttendance = this.sessionAttendanceJpaRepository.findById(sessionId);
        if (optionalSessionAttendance.isPresent()) {
            List<StudentAttendanceDto> studentAttendances = this.studentAttendanceJpaRepository
                    .findBySessionAttendanceId(optionalSessionAttendance.get().getId()).stream().map(sAtt -> this.modelMapper.map(sAtt, StudentAttendanceDto.class))
                    .collect(Collectors.toList());
            SessionAttendanceDto sessionAttendanceDto = this.modelMapper.map(optionalSessionAttendance.get(), SessionAttendanceDto.class);
            sessionAttendanceDto.setStudents(studentAttendances);
            return sessionAttendanceDto;
        } else return null;
    }

    @Override
    public List<SessionAttendanceGlobalInformationDto> getAdminSessions() {
        List<SessionAttendance> sessionAttendances = this.sessionAttendanceJpaRepository.findAll(Sort.by("date").descending() );
        return sessionAttendances.stream().map(sa -> this.modelMapper.map(sa, SessionAttendanceGlobalInformationDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<SessionAttendanceGlobalInformationDto> getSessionForStudent() {
        String userName = this.userDetailsProvider.getCurrentUserDetails().getUserName();
        Student student = this.studentsJpaRepository.findByUserName(userName).orElse(null);
        List<SessionAttendance> sessionAttendances = this.sessionAttendanceJpaRepository.findByClassId(student.getAClass().getId());
        return sessionAttendances.stream().map(s-> this.modelMapper.map(s, SessionAttendanceGlobalInformationDto.class)).collect(Collectors.toList());
    }
}
