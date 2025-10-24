package edu.fra.uas.hw_project;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.fra.uas.service.Studiengang;
import edu.fra.uas.service.Kurs;

@SpringBootTest
public class StudiengangTest {

    @Autowired
        private Studiengang testStudiengang;

    @Test
    void testStudiengang(){
    
        assertThat(testStudiengang).isNotNull();

        Kurs kurs1 = new Kurs("TestKurs1", 101, 1);
        kurs1.addNote(1.0, 2);
        kurs1.addNote(4.0, 1);

        testStudiengang.addKurs(kurs1);

        assertThat(testStudiengang.berechneNotendurchschnitt()).isNotNull();

    }
    
}
