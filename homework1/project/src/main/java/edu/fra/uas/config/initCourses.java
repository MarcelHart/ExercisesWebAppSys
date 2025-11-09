package edu.fra.uas.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.fra.uas.service.Studiengang;
import edu.fra.uas.service.Kurs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class initCourses {

    private static final Logger log = LoggerFactory.getLogger(initCourses.class);

    @Autowired
    private Studiengang studiengang;

    @PostConstruct
    public void init() {
        if (studiengang.getKurse() == null || studiengang.getKurse().isEmpty()) {
            log.debug("initCourses @PostConstruct: populating default courses");
            Kurs winfo = new Kurs("Wirtschaftsinformatik", 301, 3);
            winfo.addNote(1.3, 2);
            winfo.addNote(2.0, 3);
            studiengang.addKurs(winfo);

            Kurs algebra = new Kurs("Algebra", 201, 2);
            algebra.addNote(2.3, 1);
            algebra.addNote(4.7, 2);
            studiengang.addKurs(algebra);
        } else {
            log.debug("initCourses @PostConstruct: studiengang already initialized, skipping");
        }
    }
}
