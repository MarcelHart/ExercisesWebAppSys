package edu.fra.uas.dto;

import java.util.List;
import org.springframework.hateoas.RepresentationModel;

public class KursDTO extends RepresentationModel<KursDTO> {
    
    private int code;
    private String name;
    private int semester;
    private List<Double> noten;
    private List<Integer> gewichtungen;
    private double durchschnitt;

    public KursDTO() {}

    public KursDTO(int code, String name, int semester, List<Double> noten, 
                   List<Integer> gewichtungen, double durchschnitt) {
        this.code = code;
        this.name = name;
        this.semester = semester;
        this.noten = noten;
        this.gewichtungen = gewichtungen;
        this.durchschnitt = durchschnitt;
    }

    // Getters and Setters
    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getSemester() { return semester; }
    public void setSemester(int semester) { this.semester = semester; }

    public List<Double> getNoten() { return noten; }
    public void setNoten(List<Double> noten) { this.noten = noten; }

    public List<Integer> getGewichtungen() { return gewichtungen; }
    public void setGewichtungen(List<Integer> gewichtungen) { this.gewichtungen = gewichtungen; }

    public double getDurchschnitt() { return durchschnitt; }
    public void setDurchschnitt(double durchschnitt) { this.durchschnitt = durchschnitt; }
}