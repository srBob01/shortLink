package ru.arsentiev.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryReadDto {
    private Short id;
    private String title;
}
