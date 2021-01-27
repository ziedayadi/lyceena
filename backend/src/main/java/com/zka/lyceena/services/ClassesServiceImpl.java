package com.zka.lyceena.services;

import com.zka.lyceena.constants.CacheNames;
import com.zka.lyceena.constants.StaticData;
import com.zka.lyceena.dao.*;
import com.zka.lyceena.dto.*;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.entities.classes.ClassMaterialSession;
import com.zka.lyceena.entities.material.LevelMaterialNumberOfHours;
import com.zka.lyceena.entities.ref.ClassLevelRef;
import com.zka.lyceena.entities.ref.DayWeekRef;
import com.zka.lyceena.entities.ref.HourDayRef;
import com.zka.lyceena.entities.rooms.ClassRoom;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
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

    @Autowired
    private ClassRoomsService classRoomsService;

    @Cacheable(CacheNames.CLASSES)
    @Override
    public List<Class> findAll() {
        return this.classesJpaRepository.findAll();
    }

    @CacheEvict(value= CacheNames.CLASSES, allEntries=true)
    @Override
    public void save(ClassDto dto) {
        Class classEntity = this.classesJpaRepository.findById(dto.getId()).orElse(new Class());
        classEntity.setName(dto.getName());
        ClassLevelRef classLevelRef = this.classLevelRefJpaRepository.findById(dto.getLevelId()).get();
        classEntity.setLevel(classLevelRef);
        this.classesJpaRepository.save(classEntity);
    }

    @CacheEvict(value= CacheNames.CLASSES, allEntries=true)
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
        List<TeacherDto> teachers = this.findTeachersByClassId(id);
        List<LevelMaterialNumberOfHours> materials = this.levelMaterialNumberOfHoursJpaRepository.findByLevelId(aClass.getLevel().getId());
        materials.forEach(material -> {
            TeacherDto teacherDto = teachers.stream().filter(t->t.getMaterial().getId().equals(material.getMaterial().getId())).findAny().orElseThrow();
            Teacher teacherEntity = this.teachersJpaRepository.findById(teacherDto.getId()).orElseThrow();
            for(int i = 0; i< material.getHourNumberPerWeek() ; i++){

                ClassMaterialSession session = new ClassMaterialSession();
                session.setClazz(aClass);
                session.setMaterial(material.getMaterial());
                session.setTeacher(teacherEntity);

                // Set the start hour and end hour
                List<DayHourDto> freeDayHoursForClass = this.getFreeHourInWeekForClass(id);
                List<DayHourDto> freeDayHoursForTeacher = this.getFreeDayHourForTeacher(teacherEntity.getId());
                List<DayHourDto> intersection = getIntersection(freeDayHoursForClass, freeDayHoursForTeacher);
                if(intersection.size()>0){
                    DayHourDto dayHour = intersection.get(0);
                    session.setDayOfWeek(dayHour.getDay());
                    session.setStartHour(dayHour.getHour());
                    session.setEndHour(this.hourDayRefJpaRepository.findById(dayHour.getDay().getId()+1).orElseThrow());
                }

                // Set the class Room
                if(session.getDayOfWeek()!= null && session.getStartHour() != null){
                    List<ClassRoom> classRooms = this.classRoomsService.findForFreeHour(session.getDayOfWeek().getId(), session.getStartHour().getId());
                    if(classRooms!= null && classRooms.size() > 0)
                        session.setClassRoom(classRooms.get(0));
                }

                this.classMaterialSessionJpaRepository.save(session);
            }
        });


        return  this.classMaterialSessionJpaRepository.findByClassId(id)
                .stream().map(s -> this.modelMapper.map(s, ClassMaterialSessionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DayHourDto> findFreeDateHourByClassIdTeacherId(Long classId, String teacherId) {
        List<DayHourDto> freeDayHoursForClass = this.getFreeHourInWeekForClass(classId);
        List<DayHourDto> freeDayHoursForTeacher = this.getFreeDayHourForTeacher(teacherId);
        return getIntersection(freeDayHoursForClass, freeDayHoursForTeacher);
    }

    @Transactional
    @Override
    public void updateSession(UpdateSessionDto updateSessionDto) {
        ClassMaterialSession session = this.classMaterialSessionJpaRepository.findById(updateSessionDto.getOldSessionId()).orElseThrow();

        DayWeekRef day = this.dayWeekRefJpaRepository.findById(updateSessionDto.getDayId()).orElseThrow();
        HourDayRef sHour = this.hourDayRefJpaRepository.findById(updateSessionDto.getStartHourId()).orElseThrow();
        HourDayRef eHour = this.hourDayRefJpaRepository.findById(updateSessionDto.getEndHourId()).orElseThrow();
        ClassRoom classRoom = this.classRoomsService.findById(updateSessionDto.getClassRoomId());

        session.setDayOfWeek(day);
        session.setStartHour(sHour);
        session.setEndHour(eHour);
        session.setClassRoom(classRoom);
        this.classMaterialSessionJpaRepository.save(session);

    }

    private List<DayHourDto> getFreeHourInWeekForClass(Long classId){
        List<ClassMaterialSession> sessions = this.classMaterialSessionJpaRepository.findByClassId(classId).stream()
                .filter(s -> s.getDayOfWeek() != null && s.getStartHour() != null && s.getEndHour() != null)
                .collect(Collectors.toList());

        List<DayWeekRef> allDays = this.dayWeekRefJpaRepository.findAll().stream().filter(d->!d.getEn().equals("Sunday")).collect(Collectors.toList());
        List<HourDayRef> allHours = this.hourDayRefJpaRepository.findAll().stream().filter(h-> !Arrays.asList(StaticData.FORBIDDEN_START_HOURS).contains(h.getCode())).collect(Collectors.toList());
        List<DayHourDto> dayHours = new ArrayList<>();
        allHours.forEach(h -> {
            allDays.forEach(d -> {
                if(sessions.stream().noneMatch(s -> s.getDayOfWeek().getId().equals(d.getId()) && s.getStartHour().getId().equals(h.getId()))){
                    // No session found for day d and hour h
                    dayHours.add(new DayHourDto(d, h));
                }
            });
        });

        return dayHours;
    }

    private List<DayHourDto> getFreeDayHourForTeacher(String teacherId){
        List<ClassMaterialSession> sessions = this.classMaterialSessionJpaRepository.findByTeacherId(teacherId)
                .stream()
                .filter(s -> s.getStartHour() != null && s.getEndHour() != null  && s.getDayOfWeek() != null)
                .collect(Collectors.toList());

        List<DayWeekRef> allDays = this.dayWeekRefJpaRepository.findAll().stream().filter(d->!d.getEn().equals("Sunday")).collect(Collectors.toList());
        List<HourDayRef> allHours = this.hourDayRefJpaRepository.findAll().stream().filter(h->!h.getCode().equals("18:00")).collect(Collectors.toList());
        List<DayHourDto> dayHours = new ArrayList<>();
        allDays.forEach(d -> {
            allHours.forEach(h -> {
                if(sessions.stream().filter(s-> s.getDayOfWeek().getId().equals(d.getId()) && s.getStartHour().getId().equals(h.getId()) ).count()==0){
                    // No session found for day d and hour h
                    dayHours.add(new DayHourDto(d, h));
                }
            });
        });
        return dayHours;


    }

    private static List<DayHourDto> getIntersection(List<DayHourDto> list,List<DayHourDto> otherList){
       return list.stream()
                .distinct()
                .filter(otherList::contains).collect(Collectors.toList());
    }


}
