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


    private static final Integer STUDENTS_NUMBER = 1000;
    private static final Integer PARENTS_NUMBER = 500;
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
    public void initClassRef() {
        ClassLevelRef class7 = new ClassLevelRef();
        class7.setName("7 ème année de base");

        ClassLevelRef class8 = new ClassLevelRef();
        class8.setName("8 ème année de base");


        ClassLevelRef class9 = new ClassLevelRef();
        class9.setName("9 ème année de base");


        ClassLevelRef class1 = new ClassLevelRef();
        class1.setName("1 ère année de base");


        ClassLevelRef class2 = new ClassLevelRef();
        class2.setName("2 ème année de base");

        ClassLevelRef class3 = new ClassLevelRef();
        class3.setName("3 ème année de base");

        ClassLevelRef class4 = new ClassLevelRef();
        class4.setName("4 ème année de base");

        ClassLevelRef class5 = new ClassLevelRef();
        class5.setName("5 ème année de base");

        ClassLevelRef class6 = new ClassLevelRef();
        class6.setName("6 ème année de base");

        ClassLevelRef class11 = new ClassLevelRef();
        class11.setName("1 ère année secondaire");


        ClassLevelRef class12 = new ClassLevelRef();
        class12.setName("2 ère année secondaire");


        ClassLevelRef class13 = new ClassLevelRef();
        class13.setName("3 ème année secondaire Lettres");

        ClassLevelRef class22 = new ClassLevelRef();
        class22.setName("3 ème année secondaire informatique");

        ClassLevelRef class14 = new ClassLevelRef();
        class14.setName("3 ème année secondaire Sciences");


        ClassLevelRef class15 = new ClassLevelRef();
        class15.setName("3 ème année secondaire économie & gestion");


        ClassLevelRef class16 = new ClassLevelRef();
        class16.setName("4 ème année secondaire Lettres");


        ClassLevelRef class17 = new ClassLevelRef();
        class17.setName("4 ème année secondaire Sciences experimentales");


        ClassLevelRef class18 = new ClassLevelRef();
        class18.setName("4 ème année secondaire économie gestion");

        ClassLevelRef class19 = new ClassLevelRef();
        class19.setName("4 ème année secondaire sciences technique");


        ClassLevelRef class20 = new ClassLevelRef();
        class20.setName("4 ème année secondaire sciences informatique");

        ClassLevelRef class21 = new ClassLevelRef();
        class21.setName("4 ème année secondaire sciences mathématiques");



        this.entityManager.persist(class1);
        this.entityManager.persist(class2);
        this.entityManager.persist(class3);
        this.entityManager.persist(class4);
        this.entityManager.persist(class5);
        this.entityManager.persist(class6);
        this.entityManager.persist(class7);
        this.entityManager.persist(class8);
        this.entityManager.persist(class9);
        this.entityManager.persist(class11);
        this.entityManager.persist(class12);
        this.entityManager.persist(class13);
        this.entityManager.persist(class14);
        this.entityManager.persist(class15);
        this.entityManager.persist(class16);
        this.entityManager.persist(class17);
        this.entityManager.persist(class18);
        this.entityManager.persist(class19);
        this.entityManager.persist(class20);
        this.entityManager.persist(class21);
        this.entityManager.persist(class22);

    }

    @Bean
    public void initClasses() {
        this.classLevelRefJpaRepo.findAll().stream().forEach(c -> {
            Class aClass = new Class();
            aClass.setName("A");
            aClass.setLevel(c);

            Class bClass = new Class();
            bClass.setName("B");
            bClass.setLevel(c);

            Class cClass = new Class();
            cClass.setName("C");
            cClass.setLevel(c);

            this.classesJpaRepository.save(aClass);
            this.classesJpaRepository.save(bClass);
            this.classesJpaRepository.save(cClass);
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
    public void initMaterialRef() {
        Material math = new Material();
        math.setName("Math");
        math.setDescription("Mathématique");

        Material fr = new Material();
        fr.setName("Français");
        fr.setDescription("Langue Française");

        Material en = new Material();
        en.setName("Anglais");
        en.setDescription("Langue anglais");

        Material ar = new Material();
        ar.setName("Arabe");
        ar.setDescription("Langue Arabe");


        Material ph = new Material();
        ph.setName("Physique");
        ph.setDescription("Sciences physiques");

        Material tch = new Material();
        tch.setName("Technique");
        tch.setDescription("Sciences techniques");

        Material eco = new Material();
        eco.setName("Economies");
        eco.setDescription("Sciences Economiques");

        Material gestion = new Material();
        gestion.setName("Gestion");
        gestion.setDescription("Gestions et contabilité");

        Material educactionCivile = new Material();
        educactionCivile.setName("تربية مدنية");
        educactionCivile.setDescription("تربية مدنية");

        this.entityManager.persist(math);
        this.entityManager.persist(en);
        this.entityManager.persist(fr);
        this.entityManager.persist(ph);
        this.entityManager.persist(ar);
        this.entityManager.persist(tch);
        this.entityManager.persist(eco);
        this.entityManager.persist(gestion);
        this.entityManager.persist(educactionCivile);
    }

    @Bean
    public void initTeachers() {
        List<Material> materials = materialRefJpaRepository.findAll();
        Random random = new Random();

        List<Teacher> teachers = new ArrayList<>();
        for (int i = 0; i < StaticData.TEACHERS.length; i++) {
            Teacher teacher = new Teacher();
            teacher.setMaterial(materials.get(random.nextInt(materials.size())));
            teacher.setFirstName(StaticData.TEACHERS[i][0]);
            teacher.setLastName(StaticData.TEACHERS[i][1]);
            teacher.setPhoneNumber("+33789456123");
            teacher.setEmailAdress(StaticData.TEACHERS[i][0].toLowerCase() + "." + StaticData.TEACHERS[i][1] + "@school.com");
            teacher.setStatus(UserStatus.ACTIVE);
            teachers.add(teacher);
        }
        this.teachersJpaRepository.saveAll(teachers);
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
