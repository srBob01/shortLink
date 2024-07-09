package ru.arsentiev.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserReadDto {
    private Integer id;
    private String username;
    private String email;
    private String role;
}