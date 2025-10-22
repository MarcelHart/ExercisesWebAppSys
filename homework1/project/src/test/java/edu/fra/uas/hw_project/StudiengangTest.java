package edu.fra.uas.hw_project;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.fra.uas.service.Studiengang;

@SpringBootTest
public class StudiengangTest {

    @Autowired
        private Studiengang studiengang;

    @Test
    void testStudiengang(){
        assertThat(studiengang.berechneNotendurchschnitt()).isNotNull();
    }
    
}
