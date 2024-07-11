package ru.arsentiev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.arsentiev.service.UserService;

@SpringBootApplication
public class ShorterApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(ShorterApplication.class, args);
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.findUserById(3));
    }

}
