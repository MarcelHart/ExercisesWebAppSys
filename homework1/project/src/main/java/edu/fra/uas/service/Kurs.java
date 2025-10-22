package edu.fra.uas.service;

import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class Kurs {

    private String name;
    private int kurscode;
    private int semester;

    private HashMap<Double, Integer> noten = new HashMap<>();

    public Kurs() {
        this.name = "unknown";
        this.kurscode = 0;
        this.semester = 0;
    }   

    public Kurs(String name, int kurscode, int semester) {
        this.name = name;
        this.kurscode = kurscode;
        this.semester = semester;
    }


    public void addNote(double note, int gewichtung) {
        noten.put(note, gewichtung);
    }
    public void removeNote(double note) {
        noten.remove(note);
    }

    public String notenAusgeben() {
        String text = "";
        for (Double note : noten.keySet()) {
            
            text += "Note: " + note + " Gewichtung: " + noten.get(note) + "\n";
        }
        return text;
    }

    @Override
    public String toString() {
        return "Kurs{" +
                "name='" + name + '\'' +
                ", kurscode=" + kurscode +
                ", semester=" + semester +
                '}';
    }

    public double berechneNotendurchschnitt() {
        double summe = 0;
        int gesamtgewichtung = 0;
        for (Double note : noten.keySet()) {
            int gewichtung = noten.get(note);
            summe += note * gewichtung;
            gesamtgewichtung += gewichtung;
        }
        double durchschnitt = summe / gesamtgewichtung;
        return durchschnitt;
    }
}
