package ru.arsentiev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import ru.arsentiev.repository.UserRepository;

@SpringBootApplication
public class ShorterApplication implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(ShorterApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        userRepository.findAllByOrderByUsernameDesc().forEach(System.out::println);
    }

}
