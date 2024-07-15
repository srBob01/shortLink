package ru.arsentiev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import ru.arsentiev.dto.CategoryReadDto;
import ru.arsentiev.dto.LinkWriteDto;
import ru.arsentiev.dto.UserReadDto;
import ru.arsentiev.entity.Link;
import ru.arsentiev.mappers.LinkWriteMapper;
import ru.arsentiev.service.UserService;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class ShorterApplication {

    public static void main(String[] args) {
        var context = SpringApplication.run(ShorterApplication.class, args);
        UserService userService = context.getBean(UserService.class);
        System.out.println(userService.findUserById(3));
        LinkWriteDto linkWriteDto = LinkWriteDto.builder()
                .longLink("https://www.example.com")
                .linkName("Example Link")
                .categoryDto(new CategoryReadDto((short) 1, "Category Title"))
                .userDto(new UserReadDto(1, "user1", "FirstName", "LastName", "user1@example.com", "USER_ROLE"))
                .validHours(24)
                .build();
        LinkWriteMapper linkWriteMapper = context.getBean(LinkWriteMapper.class);

        Link link = linkWriteMapper.dtoToLink(linkWriteDto);

        System.out.println("Mapped Link Entity:");
        System.out.println(link);
    }

}
