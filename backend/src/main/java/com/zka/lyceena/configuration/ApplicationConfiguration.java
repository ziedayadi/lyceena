package com.zka.lyceena.configuration;


import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.zka.lyceena.dao")
@EntityScan({"com.zka.lyceena.entities.*"})
@Configuration
@ComponentScan({
        "com.zka.lyceena.controllers",
        "com.zka.lyceena.services",
})
public class ApplicationConfiguration {

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
}
