package edu.fra.uas;
import org.springframework.boot.CommandLineRunner;
// filepath: homeworkApplication.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.fra.uas.service.Studiengang;
import edu.fra.uas.service.Kurs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootApplication
public class HomeworkApplication {

	private static final Logger log = LoggerFactory.getLogger(HomeworkApplication.class);

	@Autowired
	Studiengang studiengang;

	@Bean
	CommandLineRunner init() {
		CommandLineRunner action = new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				
				log.debug("Starting Homework Application");
				studiengang.addKurs(new Kurs("Mathematik", 101));
				studiengang.addKurs(new Kurs("Informatik", 102));
				studiengang.printKurse();
				log.debug("Homework Application started successfully");
			}
		};
		return action;
	}
	


}
