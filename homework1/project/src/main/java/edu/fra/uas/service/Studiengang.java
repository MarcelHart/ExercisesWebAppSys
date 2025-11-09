package edu.fra.uas.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class Studiengang {

    private String name;
    private int studiengangscode;
    ArrayList<Kurs> kurse = new ArrayList<Kurs>();

    public Studiengang() {
        this.name = "Wirtschaftsinformatik BSc";
        this.studiengangscode = 1;
    }

    public Studiengang(String name, int studiengangscode) {
        this.name = name;
        this.studiengangscode = studiengangscode;
    }

    @PostConstruct
    private void init() {
        // set sensible defaults if not provided
        if (this.name == null) {
            this.name = "unknown";
        }
        if (this.studiengangscode == 0) {
            this.studiengangscode = 1;
        }
        if (this.kurse == null) {
            this.kurse = new ArrayList<>();
        }
    }

    @PreDestroy
    private void destroy() {
        if (kurse != null) {
            kurse.clear();
            kurse = null;
        }
        name = null;
        studiengangscode = 0;
    }


    public double berechneNotendurchschnitt() {
        double summe = 0;
        int anzahlKurse = kurse.size();
        for (Kurs k : kurse) {
            summe += k.berechneNotendurchschnitt();
        }
        return summe / anzahlKurse;
    }

    public void addKurs(Kurs kurs) {
        kurse.add(kurs);
    }

    public void removeKurs(Kurs kurs) {
        kurse.remove(kurs);
    }

    public void printKurse() {
        System.out.println("Kurse im Studiengang " + this.name + " Code: " + this.studiengangscode + ":");
        for (Kurs k : kurse) {
            System.out.println(k.toString());
            k.notenAusgeben();
        }
    }

    public List<Kurs> getKurse() {
        return kurse;
    }
}

