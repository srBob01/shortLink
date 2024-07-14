package ru.arsentiev.dto;

import lombok.*;

import java.time.LocalDateTime;

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
    private String username;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    private LocalDateTime removeDate;
}
