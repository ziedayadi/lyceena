package com.zka.lyceena.configuration;

import com.zka.lyceena.entities.material.Material;
import com.zka.lyceena.entities.ref.ClassLevelRef;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Configuration
@Transactional
public class DataInit {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public void initClassRef(){
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

        this.entityManager.persist(math);
        this.entityManager.persist(en);
        this.entityManager.persist(fr);


    }
}
