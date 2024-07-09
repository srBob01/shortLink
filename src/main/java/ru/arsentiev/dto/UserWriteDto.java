package ru.arsentiev.dto;

import lombok.*;
import ru.arsentiev.entity.Role;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserWriteDto {
    private String username;
    private String email;
    private String password;
    private Role role;
}
