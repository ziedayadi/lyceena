package com.zka.lyceena.services;

import com.zka.lyceena.dao.*;
import com.zka.lyceena.dto.ClassDto;
import com.zka.lyceena.dto.ClassMaterialSessionDto;
import com.zka.lyceena.dto.TeacherDto;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.entities.classes.ClassMaterialSession;
import com.zka.lyceena.entities.material.LevelMaterialNumberOfHours;
import com.zka.lyceena.entities.ref.ClassLevelRef;
import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.HourDayRef;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClassesServiceImpl implements ClassesService {

    @Autowired
    private ClassesJpaRepository classesJpaRepository;

    @Autowired
    private StudentsJpaRepository studentsJpaRepository;

    @Autowired
    private ClassLevelRefJpaRepository classLevelRefJpaRepository;

    @Autowired
    private TeachersJpaRepository teachersJpaRepository;

    @Autowired
    private ClassMaterialSessionJpaRepository classMaterialSessionJpaRepository;

    @Autowired
    private LevelMaterialNumberOfHoursJpaRepository levelMaterialNumberOfHoursJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DayWeekRefJpaRepository dayWeekRefJpaRepository;

    @Autowired
    private HourDayRefJpaRepository hourDayRefJpaRepository;

    @Override
    public List<Class> findAll() {
        return this.classesJpaRepository.findAll();
    }

    @Override
    public void save(ClassDto dto) {
        Class classEntity = this.classesJpaRepository.findById(dto.getId()).orElse(new Class());
        classEntity.setName(dto.getName());
        ClassLevelRef classLevelRef = this.classLevelRefJpaRepository.findById(dto.getLevelId()).get();
        classEntity.setLevel(classLevelRef);
        this.classesJpaRepository.save(classEntity);
    }

    @Override
    public void deleteById(Long id) {
        this.classesJpaRepository.deleteById(id);
    }

    @Override
    public Class findOne(Long id) {
        return this.classesJpaRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> findStudentsByClassId(Long id) {
        return this.studentsJpaRepository.findByClassId(id);
    }

    @Override
    public List<TeacherDto> findTeachersByClassId(Long id) {
        List<Teacher> teachers = this.teachersJpaRepository.findAll();
        return teachers.
                stream().
                filter(t -> t.getClasses().stream().filter(c -> c.getId() == id).count() > 0).
                map(t -> modelMapper.map(t, TeacherDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<ClassMaterialSessionDto> findSessionsByClassId(Long id) {
        return this.classMaterialSessionJpaRepository.findByClassId(id)
                .stream()
                .map(s -> modelMapper.map(s, ClassMaterialSessionDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<ClassMaterialSessionDto> createTimeSheetByClassId(Long id) {
        this.classMaterialSessionJpaRepository.deleteByClassId(id);
        Class aClass = this.classesJpaRepository.findById(id).orElseThrow();
        List<LevelMaterialNumberOfHours> materials = this.levelMaterialNumberOfHoursJpaRepository.findByLevelId(aClass.getLevel().getId());
        materials.forEach(material -> {
            for(int i = 0; i< material.getHourNumberPerWeek() ; i++){
                ClassMaterialSession session = new ClassMaterialSession();
                session.setClazz(aClass);
                session.setMaterial(material.getMaterial());
                List<DayHour> dayHours = this.getFreeHourInWeekForClass(id);
                DayHour dh = dayHours.stream().findAny().orElseThrow();
                session.setDayOfWeek(dh.day);
                session.setStartHour(dh.hour);
                session.setEndHour(this.hourDayRefJpaRepository.findById(dh.hour.getId()+1).orElseThrow());
                this.classMaterialSessionJpaRepository.save(session);
            }
        });


        return  this.classMaterialSessionJpaRepository.findByClassId(id)
                .stream().map(s -> this.modelMapper.map(s, ClassMaterialSessionDto.class))
                .collect(Collectors.toList());
    }

    private List<DayHour> getFreeHourInWeekForClass(Long classId){
        List<ClassMaterialSession> sessions = this.classMaterialSessionJpaRepository.findByClassId(classId);
        List<DayWeekRef> allDays = this.dayWeekRefJpaRepository.findAll().stream().filter(d->!d.getEn().equals("Sunday")).collect(Collectors.toList());
        List<HourDayRef> allHours = this.hourDayRefJpaRepository.findAll().stream().filter(h->!h.getCode().equals("18:00")).collect(Collectors.toList());
        List<DayHour> dayHours = new ArrayList<>();
        allDays.forEach(d -> {
            allHours.forEach(h -> {
                if(sessions.stream().filter(s-> s.getDayOfWeek().getId().equals(d.getId()) && s.getStartHour().getId().equals(h.getId()) ).count()==0){
                    // No session found for day d and hour h
                    dayHours.add(new DayHour(d, h));
                }
            });
        });

        return dayHours;
    }

    private class DayHour {
        public DayHour(DayWeekRef day, HourDayRef hour) {
            this.day = day;
            this.hour = hour;
        }

        DayWeekRef day;
        HourDayRef hour;
    }
}
