package ru.arsentiev.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class LinkWriteDto {
    @NotBlank
    private String longLink;
    @NotBlank
    private String linkName;
    private CategoryReadDto categoryDto;
    private UserReadDto userDto;
    @Positive
    private int validHours;
}
