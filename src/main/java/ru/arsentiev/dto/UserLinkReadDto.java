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
    private UserReadDto userDto;
    private LinkReadDto linkDto;
    private LocalDateTime createdTime;
    private LocalDateTime removeTime;
}
