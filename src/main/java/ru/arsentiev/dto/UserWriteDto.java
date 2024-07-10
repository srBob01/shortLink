package ru.arsentiev.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import ru.arsentiev.entity.Role;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserWriteDto {
    @Size(min = 3, max = 15)
    private String username;
    @Email
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @Size(min = 5, max = 30)
    private String password;
    private Role role;
}
