package ru.arsentiev.dsl;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class LinkFilter {
    private String username;
    private String email;
    private String linkName;
    private String titleCategory;
}
