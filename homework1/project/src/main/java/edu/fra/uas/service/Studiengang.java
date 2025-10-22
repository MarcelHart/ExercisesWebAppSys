package edu.fra.uas.service;

import java.util.ArrayList;
import org.springframework.stereotype.Component;

@Component
public class Studiengang {

    private String name;
    private int studiengangscode;
    ArrayList<Kurs> kurse = new ArrayList<Kurs>();


    public Studiengang() {
        this.name = "Wirtschaftsinformatik BSc";
        this.studiengangscode = 001;
    }

    public Studiengang(String name, int studiengangscode) {
        this.name = name;
        this.studiengangscode = studiengangscode;
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

    


}

