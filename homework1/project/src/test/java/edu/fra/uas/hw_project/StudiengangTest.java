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

    @Test
    void testDurchschnittOhneKurse(){
        Studiengang leererStudiengang = new Studiengang("LeererStudiengang", 999);
        assertThat(leererStudiengang.berechneNotendurchschnitt()).isNaN();
    }


    @Test
    void testMatheDurchschnitt(){
        Studiengang matheStudiengang = new Studiengang("MatheStudiengang", 202);

        Kurs analysis = new Kurs("Analysis", 201, 2);
        analysis.addNote(1.0, 3);
        analysis.addNote(3.0, 1);

        Kurs lineareAlgebra = new Kurs("Lineare Algebra", 202, 2);
        lineareAlgebra.addNote(2.0, 2);
        lineareAlgebra.addNote(4.0, 2);

        matheStudiengang.addKurs(analysis);
        matheStudiengang.addKurs(lineareAlgebra);

        double durchschnitt = matheStudiengang.berechneNotendurchschnitt();
        assertThat(durchschnitt).isEqualTo(2.25);
    }
    
}
