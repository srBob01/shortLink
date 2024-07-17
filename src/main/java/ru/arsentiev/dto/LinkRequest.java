package ru.arsentiev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class LinkRequest {
    @NotBlank
    @NotNull
    private String longLink;
    @NotBlank
    @NotNull
    private String linkName;
    @Positive
    private Short idCategory;
    @Positive
    private int validHours;
}
