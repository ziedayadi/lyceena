package com.zka.lyceena.configuration;

import com.zka.lyceena.dao.ClassLevelRefJpaRepository;
import com.zka.lyceena.dao.ClassesJpaRepository;
import com.zka.lyceena.entities.actors.Student;
import com.zka.lyceena.entities.classes.Class;
import com.zka.lyceena.entities.material.Material;
import com.zka.lyceena.entities.ref.ClassLevelRef;
import com.zka.lyceena.entities.ref.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Configuration
@Transactional
public class DataInit {

    private static final String[][] PEOPLE = {
            {"Maheen","Curtis"      },
            {"Anabelle","Huff"      },
            {"Kerri","Alston"       },
            {"Sania","Molloy"       },
            {"Calvin","Gregory"     },
            {"Khadeeja","Perry"     },
            {"Romario","Farley"     },
            {"Tahmina","Crossley"   },
            {"Cathy","Hume"         },
            {"Alejandro","Bautista" },
    };

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClassLevelRefJpaRepository classLevelRefJpaRepo;

    @Autowired
    private ClassesJpaRepository classesJpaRepository;

    @Bean
    public void initClassRef(){
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
    public void initClasses(){
        List<ClassLevelRef> classLevelRefs = this.classLevelRefJpaRepo.findAll();
        Class aClass = new Class();
        aClass.setLevel(classLevelRefs.get(1));
        aClass.setName("A");

        Class bClass = new Class();
        bClass.setLevel(classLevelRefs.get(1));
        bClass.setName("B");

        this.entityManager.persist(aClass);
        this.entityManager.persist(bClass);
    }

    @Bean
    public void initStudents(){
        Random random = new Random();

        List<Class> classes = this.classesJpaRepository.findAll();
        for(int i = 0; i < PEOPLE.length ; i++){
            String fName = PEOPLE[i][0];
            String lName = PEOPLE[i][1];
            Student student = new Student();
            student.setBirthDate(new Date());
            student.setFirstName(fName);
            student.setLastName(lName);
            student.setEmailAdress(fName.toLowerCase()+"."+lName.toLowerCase()+"@myschool.com");
            student.setStatus(UserStatus.ACTIVE);
            student.setAClass(classes.get(random.nextInt(2)));

            this.entityManager.persist(student);
        }
    }


    @Bean
    public void initMaterialRef(){
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



        this.entityManager.persist(math);
        this.entityManager.persist(en);
        this.entityManager.persist(fr);
        this.entityManager.persist(ph);
        this.entityManager.persist(ar);
        this.entityManager.persist(tch);
        this.entityManager.persist(eco);
        this.entityManager.persist(gestion);
    }

    @Bean
    public void initParents(){

    }
}
