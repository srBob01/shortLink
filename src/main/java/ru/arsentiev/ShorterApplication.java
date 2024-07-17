package ru.arsentiev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class ShorterApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(ShorterApplication.class, args);
    }

}
