package com.zka.lyceena.configuration;

import com.zka.lyceena.constants.StaticData;
import com.zka.lyceena.dao.*;
import com.zka.lyceena.entities.actors.Employee;
import com.zka.lyceena.entities.actors.Parent;
import com.zka.lyceena.entities.actors.Teacher;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.entities.material.Material;
import com.zka.lyceena.entities.ref.ClassLevelRef;
import com.zka.lyceena.entities.ref.EmployeeTypeRef;
import com.zka.lyceena.entities.ref.Sex;
import com.zka.lyceena.entities.ref.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Configuration
@Transactional
public class DataInit {


    private static final Integer STUDENTS_NUMBER = 100;
    private static final Integer PARENTS_NUMBER = 50;
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

    @Bean
    public void initMaterialRef() {
        Arrays.asList(StaticData.MATERIALS_REF_NAMES).stream().forEach(m -> {
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

    @Bean
    public void initClassRef() {

        List<Material> materials = this.materialRefJpaRepository.findAll();
        Arrays.asList(StaticData.CLASS_LEVELS_REF_NAMES).stream().forEach(s -> {
            ClassLevelRef classLevelRef = new ClassLevelRef();
            classLevelRef.setName(s);
            classLevelRef.setMaterials(materials);
            this.classLevelRefJpaRepo.save(classLevelRef);
        });


    }


    private Teacher getTeacher() {
        Random random = new Random();
        Teacher teacher = new Teacher();
        teacher.setFirstName(StaticData.FIRST_NAMES[random.nextInt(StaticData.FIRST_NAMES.length)]);
        teacher.setLastName(StaticData.LAST_NAMES[random.nextInt(StaticData.LAST_NAMES.length)]);
        teacher.setPhoneNumber("+216 - 22395671");
        teacher.setEmailAdress(teacher.getFirstName().toLowerCase() + "." + teacher.getLastName().toLowerCase() + "-teacher@school.com");
        teacher.setStatus(UserStatus.ACTIVE);
        return teacher;
    }


    @Bean
    public void initClasses() {
        List<Teacher> teachers = this.teachersJpaRepository.findAll();

        this.classLevelRefJpaRepo.findAll().stream().forEach(c -> {
            String[] classesNames = {"A","B","C"};
            Arrays.asList(classesNames).forEach(el -> {
                Class aClass = new Class();
                aClass.setName(el);
                aClass.setLevel(c);

                this.classesJpaRepository.save(aClass);
                aClass.getLevel().getMaterials().forEach(mat -> {
                    Teacher t = teachers.stream().filter(te-> te.getMaterial().getId() == mat.getId()).findAny().get();
                    t.getClasses().add(aClass);
                    teachersJpaRepository.save(t);
                });
            });
        });
    }

    @Bean
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

    @Bean
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
            student.setParent(parents.get(random.nextInt(StaticData.PARENTS.length)));
            student.setSex(Sex.valueOf(StaticData.SEXES[random.nextInt(2)]));

            this.entityManager.persist(student);
        }
    }

    @Bean
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

    @Bean
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
}
