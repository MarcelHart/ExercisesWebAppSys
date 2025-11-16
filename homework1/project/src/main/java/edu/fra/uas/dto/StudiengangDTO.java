package edu.fra.uas.dto;

import java.util.List;
import org.springframework.hateoas.RepresentationModel;

public class StudiengangDTO extends RepresentationModel<StudiengangDTO> {
    
    private String name;
    private int studiengangscode;
    private List<KursDTO> kurse;
    private double notendurchschnitt;

    public StudiengangDTO() {}

    public StudiengangDTO(String name, int studiengangscode, List<KursDTO> kurse, double notendurchschnitt) {
        this.name = name;
        this.studiengangscode = studiengangscode;
        this.kurse = kurse;
        this.notendurchschnitt = notendurchschnitt;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getStudiengangscode() { return studiengangscode; }
    public void setStudiengangscode(int studiengangscode) { this.studiengangscode = studiengangscode; }

    public List<KursDTO> getKurse() { return kurse; }
    public void setKurse(List<KursDTO> kurse) { this.kurse = kurse; }

    public double getNotendurchschnitt() { return notendurchschnitt; }
    public void setNotendurchschnitt(double notendurchschnitt) { this.notendurchschnitt = notendurchschnitt; }
}