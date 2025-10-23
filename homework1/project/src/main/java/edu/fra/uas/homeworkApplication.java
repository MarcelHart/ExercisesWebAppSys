package edu.fra.uas;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.fra.uas.service.Studiengang;
import edu.fra.uas.controller.NotenController;
import edu.fra.uas.service.Kurs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootApplication
public class HomeworkApplication {

	private static final Logger log = LoggerFactory.getLogger(HomeworkApplication.class);

	@Autowired
	NotenController notenController;
	Studiengang wirtschaftsinformatikerBsc;

	public static void main(String[] args) {
		SpringApplication.run(HomeworkApplication.class, args);
	}


	@Bean
	CommandLineRunner init() {
		CommandLineRunner action = new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				
				log.debug("Starting Homework Application");
					Kurs winfo = new Kurs("Wirtschaftsinformatik", 301, 3);
					winfo.addNote(1.3, 2);
					winfo.addNote(2.0, 3);

					Kurs algebra = new Kurs("Algebra", 201, 2);
					algebra.addNote(2.3, 1);
					algebra.addNote(4.7, 2);

					wirtschaftsinformatikerBsc.addKurs(winfo);
					wirtschaftsinformatikerBsc.addKurs(algebra);
					wirtschaftsinformatikerBsc.printKurse();

					double durchschnitt = wirtschaftsinformatikerBsc.berechneNotendurchschnitt();
					log.debug("Notendurchschnitt im Studiengang: " + durchschnitt);
				

				log.debug("Homework Application started successfully");
							
			}
		};
		return action;
	}
	


}
