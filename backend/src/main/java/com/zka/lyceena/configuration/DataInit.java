package com.zka.lyceena.configuration;

import com.zka.lyceena.constants.StaticData;
import com.zka.lyceena.dao.*;
import com.zka.lyceena.entities.actors.Employee;
import com.zka.lyceena.entities.actors.Parent;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.entities.material.LevelMaterialNumberOfHours;
import com.zka.lyceena.entities.material.Material;
import com.zka.lyceena.entities.ref.*;
import com.zka.lyceena.entities.rooms.ClassRoom;
import com.zka.lyceena.services.ClassesService;
import com.zka.lyceena.services.RefService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
@Transactional
public class DataInit {


    private static final Integer STUDENTS_NUMBER = 520;
    private static final Integer PARENTS_NUMBER = 50;
    private static final Integer CLASS_ROOMS_NUMBER = 25;
    private static final Integer CLASS_YEARS_NUMBER = 25;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClassLevelRefJpaRepository classLevelRefJpaRepo;

    @Autowired
    private ClassesJpaRepository classesJpaRepository;

    @Autowired
    private ParentsJpaRepository parentsJpaRepository;

    @Autowired
    private MaterialRefJpaRepository materialRefJpaRepository;

    @Autowired
    private TeachersJpaRepository teachersJpaRepository;

    @Autowired
    private EmployeeRefTypeJpaRepository employeeRefTypeJpaRepository;

    @Autowired
    private EmployeeJpaRepository employeeJpaRepository;

    @Autowired
    private DayWeekRefJpaRepository dayWeekRefJpaRepository;

    @Autowired
    private HourDayRefJpaRepository hourDayRefJpaRepository;

    @Autowired
    private ClassMaterialSessionJpaRepository classMaterialSessionJpaRepository;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private LevelMaterialNumberOfHoursJpaRepository numberOfHoursJpaRepository;

    @Autowired
    private ClassRoomJpaRepository classRoomJpaRepository;

    @Autowired
    private ClassYearJpaRepository classYearJpaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RefService refService;

    @Autowired
    private GlobalRefJpaRepository globalRefJpaRepository;

    private int teachersIndex = 0;

    @Value( "${spring.jpa.hibernate.ddl-auto}" )
    private String ddlAuto;

    @Bean
    public void initData(){
        if(this.ddlAuto.equals("create")){
            this.initClassYears();
            this.initDays();
            this.initHours();
            this.initMaterialRef();
            this.initClassRef();
            this.initNumberOfHours();
            this.initClassRooms();
            this.initClasses();
            this.initParents();
            this.initStudents();
            this.initEmployeeRefType();
            this.initEmployee();
            this.initGlobalRef();
        }
    }

    public void initClassYears(){
        for (int i = 2020; i<= 2020+CLASS_YEARS_NUMBER ; i++){
            ClassYear classYear = new ClassYear();
            classYear.setStartYear(i);
            classYear.setEndYear(i+1);
            this.classYearJpaRepository.save(classYear);
        }
    }

    public void initDays(){
        Arrays.stream(StaticData.DAYS).forEach(el -> {
            DayWeekRef dayWeekRef = new DayWeekRef();
            dayWeekRef.setEn(el[0]);
            dayWeekRef.setFr(el[1]);
            dayWeekRef.setAr(el[2]);
            this.dayWeekRefJpaRepository.save(dayWeekRef);
        });
    }

    public void initHours(){
        List<HourDayRef> hours = Arrays.stream(StaticData.HOURS).map(h-> {
            HourDayRef hourDayRef = new HourDayRef();
            hourDayRef.setCode(h);
            return hourDayRef;
        }).collect(Collectors.toList());
        this.hourDayRefJpaRepository.saveAll(hours);
    }

    public void initMaterialRef() {
        Arrays.stream(StaticData.MATERIALS_REF_NAMES).forEach(m -> {
            Material mat = new Material();
            mat.setName(m);
            mat.setDescription(m + " ...");
            this.entityManager.persist(mat);

            Teacher t1 = this.getTeacher();
            t1.setMaterial(mat);
            Teacher t2 = this.getTeacher();
            t2.setMaterial(mat);

            this.teachersJpaRepository.save(t1);
            this.teachersJpaRepository.save(t2);

        });

    }

    public void initClassRef() {

        List<Material> materials = this.materialRefJpaRepository.findAll();
        Arrays.asList(StaticData.CLASS_LEVELS_REF_NAMES).stream().forEach(s -> {
            ClassLevelRef classLevelRef = new ClassLevelRef();
            classLevelRef.setName(s);
            classLevelRef.setMaterials(materials);
            this.classLevelRefJpaRepo.save(classLevelRef);
        });


    }

    public void initNumberOfHours(){
        List<ClassLevelRef>  levels = this.classLevelRefJpaRepo.findAll();
        levels.forEach(l-> {
            List<LevelMaterialNumberOfHours> numberOfHoursList = new ArrayList<>();
            l.getMaterials().forEach(lm -> {
                LevelMaterialNumberOfHours numberOfHours = new LevelMaterialNumberOfHours();
                numberOfHours.setClassLevelRef(l);
                numberOfHours.setMaterial(lm);
                numberOfHours.setHourNumberPerWeek(2);
                numberOfHoursList.add(numberOfHours);
            });
            this.numberOfHoursJpaRepository.saveAll(numberOfHoursList);
        });
    }

    public void initClassRooms(){
        List<ClassRoom> classRooms = new ArrayList<>();
        for (int i  = 1; i <= CLASS_ROOMS_NUMBER ; i++){
            ClassRoom classRoom = new ClassRoom();
            classRoom.setName("C-"+i);
            classRoom.setCapacity(30);
            classRooms.add(classRoom);
        }
        this.classRoomJpaRepository.saveAll(classRooms);
    }

    public void initClasses() {
        List<Teacher> teachers = this.teachersJpaRepository.findAll();

        this.classLevelRefJpaRepo.findAll().stream().forEach(c -> {
            String[] classesNames = {"A"};
            Arrays.asList(classesNames).forEach(el -> {
                Class aClass = new Class();
                aClass.setName(el);
                aClass.setLevel(c);
                aClass.setClassYear(this.refService.getCurrentClassYear());
                this.classesJpaRepository.save(aClass);
                /*
                *******************************************************************
                ******************** INIT teachers for a class ********************
                *******************************************************************
                aClass.getLevel().getMaterials().forEach(mat -> {
                    Teacher t = teachers.stream().filter(te-> te.getMaterial().getId() == mat.getId()).findAny().get();
                    t.getClasses().add(aClass);
                    teachersJpaRepository.save(t);
                });
                 */
            });
        });
    }

    public void initParents() {
        List<Parent> parents = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < PARENTS_NUMBER; i++) {
            Parent parent = new Parent();
            parent.setFirstName(StaticData.FIRST_NAMES[random.nextInt(StaticData.FIRST_NAMES.length)]);
            parent.setLastName(StaticData.LAST_NAMES[random.nextInt(StaticData.LAST_NAMES.length)]);
            parent.setEmailAdress(parent.getFirstName().toLowerCase() + "." + parent.getLastName() + "-parent@school.com");
            parent.setStatus(UserStatus.ACTIVE);
            parent.setPhoneNumber("+33612345678");
            parents.add(parent);
        }

        this.parentsJpaRepository.saveAll(parents);
    }

    public void initStudents() {
        Random random = new Random();

        List<Class> classes = this.classesJpaRepository.findAll();
        List<Parent> parents = this.parentsJpaRepository.findAll();
        for (int i = 0; i < STUDENTS_NUMBER; i++) {
            String fName = StaticData.FIRST_NAMES[random.nextInt(StaticData.FIRST_NAMES.length)];
            String lName = StaticData.LAST_NAMES[random.nextInt(StaticData.LAST_NAMES.length)];
            Student student = new Student();
            student.setBirthDate(new Date());
            student.setFirstName(fName);
            student.setLastName(lName);
            student.setEmailAdress(fName.toLowerCase() + "." + lName.toLowerCase() + "@myschool.com");
            student.setStatus(UserStatus.ACTIVE);
            student.setAClass(classes.get(random.nextInt(classes.size())));
            student.setParent(parents.get(random.nextInt(parents.size())));
            student.setSex(Sex.valueOf(StaticData.SEXES[random.nextInt(2)]));
            this.entityManager.persist(student);
        }
    }

    public void initEmployeeRefType(){
        EmployeeTypeRef type1 = new EmployeeTypeRef();
        type1.setId(1);
        type1.setName("Administration");
        this.employeeRefTypeJpaRepository.save(type1);

        EmployeeTypeRef type2 = new EmployeeTypeRef();
        type2.setId(2);
        type2.setName("Ménage");
        this.employeeRefTypeJpaRepository.save(type2);


        EmployeeTypeRef type3 = new EmployeeTypeRef();
        type3.setId(3);
        type3.setName("Sécurité");
        this.employeeRefTypeJpaRepository.save(type3);
    }

    public void initEmployee(){
        List<EmployeeTypeRef> refs = this.employeeRefTypeJpaRepository.findAll();
        Employee e1 = new Employee();
        e1.setFirstName("Mabrouka");
        e1.setLastName("Mannai");
        e1.setStatus(UserStatus.ACTIVE);
        e1.setEmailAdress("mabdouka.mannai@yahoo.fr");
        e1.setType(refs.get(1));
        this.employeeJpaRepository.save(e1);

        Employee e3 = new Employee();
        e3.setFirstName("Monjia");
        e3.setLastName("Ben Salem");
        e3.setStatus(UserStatus.ACTIVE);
        e3.setEmailAdress("mbensalem@yahoo.fr");
        e3.setType(refs.get(1));
        this.employeeJpaRepository.save(e3);

        Employee e2 = new Employee();
        e2.setFirstName("Mouldi");
        e2.setLastName("Ayari");
        e2.setStatus(UserStatus.ACTIVE);
        e2.setEmailAdress("mouldi.ayari@yahoo.fr");
        e2.setType(refs.get(2));
        this.employeeJpaRepository.save(e2);

        Employee e4 = new Employee();
        e4.setFirstName("Mohamed Ali");
        e4.setLastName("Ben Ammar");
        e4.setStatus(UserStatus.ACTIVE);
        e4.setEmailAdress("m.ben.ali.b.ammar@outlook.fr");
        e4.setType(refs.get(0));
        this.employeeJpaRepository.save(e4);

        Employee e5 = new Employee();
        e5.setFirstName("Samia");
        e5.setLastName("Jammali");
        e5.setStatus(UserStatus.ACTIVE);
        e5.setEmailAdress("samia.jammali@outlook.fr");
        e5.setType(refs.get(0));
        this.employeeJpaRepository.save(e5);


        Employee e6 = new Employee();
        e6.setFirstName("Lamia");
        e6.setLastName("Khammassi");
        e6.setStatus(UserStatus.ACTIVE);
        e6.setEmailAdress("lamia.khamassi@outlook.fr");
        e6.setType(refs.get(0));
        this.employeeJpaRepository.save(e6);
    }

    private Teacher getTeacher() {
        Random random = new Random();
        Teacher teacher = new Teacher();
        teacher.setFirstName(StaticData.TEACHERS[teachersIndex][0]);
        teacher.setLastName(StaticData.TEACHERS[teachersIndex][1]);
        teachersIndex++;
        String userName = teacher.getFirstName().substring(0,1).toLowerCase() +  teacher.getLastName().toLowerCase().replace(" ","");
        teacher.setPhoneNumber("+216 - 22395671");
        teacher.setEmailAdress(teacher.getFirstName().toLowerCase() + "." + teacher.getLastName().toLowerCase() + "-teacher@school.com");
        teacher.setStatus(UserStatus.ACTIVE);
        teacher.setUserName(userName);
        return teacher;
    }

    public void initGlobalRef(){
        GlobalRef schoolName = new GlobalRef();
        schoolName.setCode("SCHOOL_NAME");
        schoolName.setValue("Les élites");

        GlobalRef env = new GlobalRef();
        env.setCode("ENV");
        env.setValue("DEV-H2");

        GlobalRef phone = new GlobalRef();
        phone.setCode("SCHOOL_PHONE");
        phone.setValue("+33 01 25 26 45 77");

        GlobalRef schoolAddress = new GlobalRef();
        schoolAddress.setCode("SCHOOL_ADDRESS");
        schoolAddress.setValue("123 Rue Toto 12345 Titi - France");

        GlobalRef schoolEmail = new GlobalRef();
        schoolEmail.setCode("SCHOOL_EMAIL");
        schoolEmail.setValue("les.elites@lyceena.fr");

        this.globalRefJpaRepository.save(schoolName);
        this.globalRefJpaRepository.save(schoolEmail);
        this.globalRefJpaRepository.save(schoolAddress);
        this.globalRefJpaRepository.save(env);
        this.globalRefJpaRepository.save(phone);
    }


}
