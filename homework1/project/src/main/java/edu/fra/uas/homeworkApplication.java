package edu.fra.uas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.CommandLineRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.fra.uas.service.Studiengang;
import edu.fra.uas.service.Kurs;

@SpringBootApplication
public class HomeworkApplication {

    private static final Logger log = LoggerFactory.getLogger(HomeworkApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HomeworkApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(Studiengang studiengang) {
        return args -> {
            // guard to avoid duplicate initialization if initCourses already ran
            if (studiengang.getKurse() == null || studiengang.getKurse().isEmpty()) {
                log.debug("CommandLineRunner: populating sample courses");
                Kurs winfo = new Kurs("Wirtschaftsinformatik", 301, 3);
                winfo.addNote(1.3, 2);
                winfo.addNote(2.0, 3);
                studiengang.addKurs(winfo);

                Kurs algebra = new Kurs("Algebra", 201, 2);
                algebra.addNote(2.3, 1);
                algebra.addNote(4.7, 2);
                studiengang.addKurs(algebra);
            } else {
                log.debug("CommandLineRunner: courses already present, skipping population");
            }
        };
    }
}
