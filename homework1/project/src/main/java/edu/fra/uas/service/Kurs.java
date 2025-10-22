package edu.fra.uas.service;

import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class Kurs {

    private String name;
    private int kurscode;
    private int semester;

    private HashMap<Double, Integer> noten = new HashMap<>();


    public Kurs(String name, int kurscode, int semester) {
        this.name = name;
        this.kurscode = kurscode;
        this.semester = semester;
    }


    public void addNote(double note, int gewichtung) {
        noten.put(note, anzahl);
    }
    public void removeNote(double note) {
        noten.remove(note);
    }

    public void notenAusgeben() {
        for (Double note : noten.keySet()) {
            System.out.println("Note: " + note + " Gewichtung: " + noten.get(note));
        }
    }

    public void berechneNotendurchschnitt() {
        double summe = 0;
        int gesamtgewichtung = 0;
        for (Double note : noten.keySet()) {
            int gewichtung = noten.get(note);
            summe += note * gewichtung;
            gesamtgewichtung += gewichtung;
        }
        double durchschnitt = summe / gesamtgewichtung;
        System.out.println("Notendurchschnitt für den Kurs "+ this.name + " ist: " + durchschnitt);
    
    }
}
