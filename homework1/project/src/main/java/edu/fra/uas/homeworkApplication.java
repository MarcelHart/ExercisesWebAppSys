package edu.fra.uas;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import edu.fra.uas.service.Studiengang;
import edu.fra.uas.controller.NotenController;
import edu.fra.uas.service.Kurs;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootApplication
public class HomeworkApplication {

	private static final Logger log = LoggerFactory.getLogger(HomeworkApplication.class);

	@Autowired
	NotenController notenController;
	@Autowired
	Studiengang wirtschaftsinformatikerBsc;
	@Autowired
	Scanner input;

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
							
				while (true) {
					log.debug("Geben Sie 'exit' ein, um die Anwendung zu beenden.\n" +
					"Kurs hinzufuegen: add \n" +
					"Kurs entfernen: remove \n" +
					"Kurse anzeigen: print \n" +
					"Durchschnitt berechnen: average\n" +
					"Note hinzufuegen: addnote \n" +
					"Note entfernen: removenote \n" +
					"Noten anzeigen: printnotes");

					String command = input.nextLine();
					if (command.equalsIgnoreCase("exit")) {
						log.debug("Beenden der Anwendung...");
						break;
					} else if (command.equalsIgnoreCase("add")) {
						// Code zum Hinzufügen eines Kurses
						log.debug("Kurs hinzufuegen - Funktion noch nicht implementiert.");
					} else if (command.equalsIgnoreCase("remove")) {
						// Code zum Entfernen eines Kurses
						log.debug("Kurs entfernen - Funktion noch nicht implementiert.");
					} else if (command.equalsIgnoreCase("print")) {
						wirtschaftsinformatikerBsc.printKurse();
					} else if (command.equalsIgnoreCase("average")) {
						double avg = wirtschaftsinformatikerBsc.berechneNotendurchschnitt();
						log.debug("Notendurchschnitt: " + avg);
					} else if (command.equalsIgnoreCase("addnote")) {
						// Code zum Hinzufügen einer Note
						log.debug("Note hinzufuegen - Funktion noch nicht implementiert.");
					} else if (command.equalsIgnoreCase("removenote")) {
						// Code zum Entfernen einer Note
						log.debug("Note entfernen - Funktion noch nicht implementiert.");
					} else if (command.equalsIgnoreCase("printnotes")) {
						// Code zum Anzeigen der Noten
						log.debug("Noten anzeigen - Funktion noch nicht implementiert.");
					} 
					else {
						log.debug("Unbekannter Befehl: " + command);
					}
				}
			}
		};
		return action;
	}
	


}
