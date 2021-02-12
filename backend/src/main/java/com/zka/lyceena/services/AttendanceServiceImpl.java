package com.zka.lyceena.services;

import com.zka.lyceena.dao.ClassMaterialSessionJpaRepository;
import com.zka.lyceena.dao.SessionAttendanceJpaRepository;
import com.zka.lyceena.dao.StudentAttendanceJpaRepository;
import com.zka.lyceena.dao.StudentsJpaRepository;
import com.zka.lyceena.dto.attendance.SaveStudentAttendanceDto;
import com.zka.lyceena.dto.attendance.SessionAttendanceDto;
import com.zka.lyceena.dto.attendance.StudentAttendanceDto;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.attendance.SessionAttendance;
import com.zka.lyceena.entities.attendance.StudentAttendance;
import com.zka.lyceena.entities.classes.ClassMaterialSession;
import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.HourDayRef;
import com.zka.lyceena.entities.ref.StudentAttendanceValue;
import com.zka.lyceena.security.UserDetails;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
                // Session attendance is present
                sessionAttendanceDto = this.modelMapper.map(sessionAttendance.get(), SessionAttendanceDto.class);

                //Get Students Attendances
                List<StudentAttendance> studentAttendances = this.studentAttendanceJpaRepository.findBySessionAttendanceId(sessionAttendance.get().getId());
                sessionAttendanceDto.setStudents(studentAttendances.stream().map(s->modelMapper.map(s, StudentAttendanceDto.class)).collect(Collectors.toList()));

            } else  {
                // Session attendance is not present => Create new one
                SessionAttendance newSessionAttendance = new SessionAttendance();
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
}
