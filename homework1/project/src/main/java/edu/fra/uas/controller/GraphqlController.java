package edu.fra.uas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import edu.fra.uas.exception.KursNotFoundException;
import edu.fra.uas.service.Kurs;
import edu.fra.uas.service.Studiengang;

@Controller
public class GraphqlController {

    @Autowired
    private Studiengang studiengang;

    private Kurs findKurs(int code) {
        return studiengang.getKurse().stream()
                .filter(k -> k.getCode() == code)
                .findFirst()
                .orElseThrow(() -> new KursNotFoundException(code));
    }

    @QueryMapping
    public List<Kurs> kurse() {
        return studiengang.getKurse();
    }

    @QueryMapping
    public Kurs kurs(@Argument int code) {
        return findKurs(code);
    }

    @QueryMapping
    public Studiengang studiengang() {
        return studiengang;
    }

    @MutationMapping
    public Kurs createKurs(@Argument int code, @Argument String name, @Argument int semester) {
        Kurs k = new Kurs(name, code, semester);
        studiengang.addKurs(k);
        return k;
    }

    @MutationMapping
    public Boolean deleteKurs(@Argument int code) {
        return studiengang.getKurse().removeIf(k -> k.getCode() == code);
    }

    @MutationMapping
    public Kurs addNote(@Argument int code, @Argument double note, @Argument int gewichtung) {
        Kurs k = findKurs(code);
        k.addNote(note, gewichtung);
        return k;
    }

    @MutationMapping
    public Kurs deleteNote(@Argument int code, @Argument int index) {
        Kurs k = findKurs(code);
        k.removeNote(index);
        return k;
    }
}