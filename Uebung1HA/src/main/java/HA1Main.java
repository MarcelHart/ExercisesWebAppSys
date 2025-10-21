import java.beans.BeanProperty;

@SpringBootApplication
public class HA1Main {

    	private static final Logger log = LoggerFactory.getLogger(BeanExampleApplication.class);

    @Autowired
    private Studiengang studiengang;


    @Bean
    CommandLineRunner init() {
        CommandLineRunner action = new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Kurs kurs1 = new Kurs("Mathematik", 101, 1);
                kurs1.addNote(1.3, 3);
                kurs1.addNote(2.0, 2);
                kurs1.notenAusgeben();
                kurs1.berechneNotendurchschnitt();

                Kurs kurs2 = new Kurs("Informatik", 102, 1);
                kurs2.addNote(1.0, 4);
                kurs2.addNote(2.3, 1);
                kurs2.notenAusgeben();
                kurs2.berechneNotendurchschnitt();

                studiengang.addKurs(kurs1);
                studiengang.addKurs(kurs2);
                studiengang.berechneNotendurchschnitt();
            }
        };
        return action;
    }


}
