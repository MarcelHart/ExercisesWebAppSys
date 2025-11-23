package edu.fra.uas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PreDestroy;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
public class ScannerConfig {

    private static final Logger log = LoggerFactory.getLogger(ScannerConfig.class);
    
    private Scanner scannerInstance;

    @Bean
    public Scanner scanner() {
        scannerInstance = new Scanner(System.in);
        return scannerInstance;
    }

    @PreDestroy
    public void closeScanner() {
        if (scannerInstance != null) {
            log.debug("Closing Scanner bean");
            scannerInstance.close();
        }
    }
}