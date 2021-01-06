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
        class7.setName("7 ème année");

        ClassLevelRef class8 = new ClassLevelRef();
        class8.setName("8 ème année");


        ClassLevelRef class9 = new ClassLevelRef();
        class9.setName("9 ème année");


        ClassLevelRef class1 = new ClassLevelRef();
        class1.setName("1 ère année");


        ClassLevelRef class2 = new ClassLevelRef();
        class2.setName("2 ème année");

        this.entityManager.persist(class1);
        this.entityManager.persist(class2);
        this.entityManager.persist(class7);
        this.entityManager.persist(class8);
        this.entityManager.persist(class9);

    }

    @Bean
    public void initClasses() {
        this.classLevelRefJpaRepo.findAll().stream().forEach(c -> {
            Class aClass = new Class();
            aClass.setName("A");
            aClass.setLevel(c);

            Class bClass = new Class();
            bClass.setName("A");
            bClass.setLevel(c);

            this.classesJpaRepository.save(aClass);
            this.classesJpaRepository.save(bClass);
        });
    }

    @Bean
    public void initParents() {
        List<Parent> parents = new ArrayList<>();
        for (int i = 0; i < StaticData.PARENTS.length; i++) {
            Parent parent = new Parent();
            parent.setFirstName(StaticData.PARENTS[i][0]);
            parent.setLastName(StaticData.PARENTS[i][1]);
            parent.setEmailAdress(StaticData.PARENTS[i][0].toLowerCase() + "." + StaticData.PARENTS[i][1] + "@school.com");
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
        for (int i = 0; i < StaticData.STUDENTS.length; i++) {
            String fName = StaticData.STUDENTS[i][0];
            String lName = StaticData.STUDENTS[i][1];
            Student student = new Student();
            student.setBirthDate(new Date());
            student.setFirstName(fName);
            student.setLastName(lName);
            student.setEmailAdress(fName.toLowerCase() + "." + lName.toLowerCase() + "@myschool.com");
            student.setStatus(UserStatus.ACTIVE);
            student.setAClass(classes.get(random.nextInt(classes.size())));
            student.setParent(parents.get(random.nextInt(StaticData.PARENTS.length)));

            List<Sex> SEXES = Collections.unmodifiableList(Arrays.asList(Sex.values()));
            student.setSex(SEXES.get(random.nextInt(SEXES.size())));

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
