package ru.arsentiev.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserLogPasDto {
    private String email;
    private String password;
}
