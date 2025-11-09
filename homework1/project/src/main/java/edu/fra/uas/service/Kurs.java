package edu.fra.uas.service;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Kurs {

    private String name;
    private int code;
    private int semester;
    private List<Double> noten = new ArrayList<>();
    private List<Integer> gewichtungen = new ArrayList<>();

    public Kurs() {
        this.name = "unknown";
        this.code = 0;
        this.semester = 0;
    }   

    public Kurs(String name, int code, int semester) {
        this.name = name;
        this.code = code;
        this.semester = semester;
    }

    // Add getters
    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public List<Double> getNoten() {
        return noten;
    }

    public List<Integer> getGewichtungen() {
        return gewichtungen;
    }

    public void addNote(double note, int gewichtung) {
        noten.add(note);
        gewichtungen.add(gewichtung);
    }
    public void removeNote(int index) {
        if (index >= 0 && index < noten.size()) {
            noten.remove(index);
            gewichtungen.remove(index);
        }
    }

    public String notenAusgeben() {
        String text = "";
        for (int i = 0; i < noten.size(); i++) {
            text += "Note: " + noten.get(i) + " Gewichtung: " + gewichtungen.get(i) + "\n";
        }
        return text;
    }

    @Override
    public String toString() {
        return "Kurs{" +
                "name='" + name + '\'' +
                ", code=" + code +
                ", semester=" + semester +
                '}';
    }

    public double berechneNotendurchschnitt() {
        double summe = 0;
        int gesamtgewichtung = 0;
        for (int i = 0; i < noten.size(); i++) {
            double note = noten.get(i);
            int gewichtung = gewichtungen.get(i);
            summe += note * gewichtung;
            gesamtgewichtung += gewichtung;
        }
        double durchschnitt = summe / gesamtgewichtung;
        return durchschnitt;
    }
}
