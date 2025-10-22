package project.src.main.java.edu.fra.uas;
// filepath: homeworkApplication.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootApplication
public class homeworkApplication {

	private static final Logger log = LoggerFactory.getLogger(homeworkApplication.class);

	@Autowired
	Studiengang studiengang;
	public static void main(String[] args) {
		SpringApplication.run(homeworkApplication.class, args);
	}
	


}
