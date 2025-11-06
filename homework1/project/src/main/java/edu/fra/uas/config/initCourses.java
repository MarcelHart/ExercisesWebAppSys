package edu.fra.uas.config;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import edu.fra.uas.service.Kurs;

@Component
public class initCourses {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(initCourses.class);
    
    @Autowired
    Kurs initKurs;


    @PostConstruct
    public void init() {
        log.debug("### Initialize Courses ###");

        log.debug("create course Software Engineering");
        initKurs = new Kurs("Software Engineering", 101, 3);
        initKurs.addNote(1.0, 2);
        initKurs.addNote(2.3, 4);
        log.debug("Course created: " + initKurs.toString());
        log.debug("Grades:\n" + initKurs.notenAusgeben());

        log.debug("### Courses initialized ###");
    }

}
