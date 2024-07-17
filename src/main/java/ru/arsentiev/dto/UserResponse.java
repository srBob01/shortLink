package ru.arsentiev.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class UserResponse {
    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
