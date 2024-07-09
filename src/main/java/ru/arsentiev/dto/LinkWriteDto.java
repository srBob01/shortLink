package ru.arsentiev.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class LinkWriteDto {
    private String shortLink;
    private String longLink;
    private CategoryReadDto categoryDto;
}
