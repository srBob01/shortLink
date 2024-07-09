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
    private Integer idUser;
    private Long idLink;
    private LocalDateTime createdTime;
    private LocalDateTime removeTime;
}
