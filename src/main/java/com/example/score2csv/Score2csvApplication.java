package com.example.score2csv;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Score2csvApplication {

    @Value("${file.location}")
    String fileLocation;

    public static void main(String[] args) {
        SpringApplication.run(Score2csvApplication.class, args);
    }

    @Bean
    public String fileLocation() {
        return fileLocation;
    }
}
