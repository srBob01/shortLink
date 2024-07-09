package ru.arsentiev.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserLinkReadDto {
    private Long id;
    private String username;
    private String shortLink;
    private String title;
    private LocalDateTime createdTime;
    private LocalDateTime removeTime;
}
