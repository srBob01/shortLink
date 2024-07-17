package ru.arsentiev.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CategoryResponse {
    private Short id;
    private String title;
}
