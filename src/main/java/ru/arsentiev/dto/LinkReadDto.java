package ru.arsentiev.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class LinkReadDto {
    private Long id;
    private String shortLink;
    private String longLink;
    private String linkName;
    private String titleCategory;

}
