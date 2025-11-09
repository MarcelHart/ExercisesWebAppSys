package edu.fra.uas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PreDestroy;
import java.util.Scanner;

@Configuration
public class ScannerConfig {
    
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }

    @PreDestroy // Close the scanner when the application context is destroyed
    public void closeScanner() {
        scanner().close();
    }
}