package com.zka.lyceena;

import com.zka.lyceena.configuration.ApplicationConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
		scanBasePackageClasses = ApplicationConfiguration.class)
public class LyceenaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LyceenaApplication.class, args);
	}

}
