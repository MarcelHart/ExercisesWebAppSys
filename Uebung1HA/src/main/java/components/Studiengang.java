package main.java.components;

@Component
public class Studiengang {

    @Autowired
    private Kurs kurs;

    private String name;
    private int studiengangscode;
    ArrayList<Kurs> kurse = new ArrayList<Kurs>();

    public Studiengang(String name, int studiengangscode) {
        this.name = name;
        this.studiengangscode = studiengangscode;
    }


    public void berechneNotendurchschnitt() {
        double summe = 0;
        int anzahlKurse = kurse.size();
        for (Kurs k : kurse) {
            k.berechneNotendurchschnitt();
        }
    }

    public void addKurs(Kurs kurs) {
        kurse.add(kurs);
    }

    public void removeKurs(Kurs kurs) {
        kurse.remove(kurs);
    }

    public void printKurse() {
        for (Kurs k : kurse) {
            System.out.println(k.toString());
        }
    }

    


}
