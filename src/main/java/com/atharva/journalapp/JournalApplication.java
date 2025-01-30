package com.atharva.journalapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class JournalApplication {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
        System.setProperty("DATABASE_USER", dotenv.get("DATABASE_USER"));
        System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
        System.setProperty("DATABASE_ACTIVE", dotenv.get("DATABASE_ACTIVE"));
        SpringApplication.run(JournalApplication.class, args);
    }



}
