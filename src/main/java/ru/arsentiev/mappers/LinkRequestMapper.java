package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;
import ru.arsentiev.dto.LinkRequest;
import ru.arsentiev.entity.Link;
import ru.arsentiev.entity.User;
import ru.arsentiev.url.UrlShortenerService;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public abstract class LinkRequestMapper {
    @Autowired
    private UrlShortenerService urlShortenerService;

    @Mapping(source = "linkRequest.idCategory", target = "category.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "user", target = "user")
    @Mapping(target = "removeDate", expression = "java(calculateRemoveDate(linkRequest.getValidHours()))")
    @Mapping(target = "shortLink", expression = "java(generateShortLink(linkRequest))")
    public abstract Link dtoToLink(LinkRequest linkRequest, User user);

    @Mapping(source = "linkRequest.idCategory", target = "category.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "shortLink", ignore = true)
    @Mapping(target = "removeDate", expression = "java(calculateRemoveDate(linkRequest.getValidHours()))")
    public abstract void updateLinkFromDto(LinkRequest linkRequest, @MappingTarget Link link);


    protected LocalDateTime calculateRemoveDate(int validHours) {
        return LocalDateTime.now().plusHours(validHours);
    }

    protected String generateShortLink(LinkRequest linkRequest) {
        try {
            return urlShortenerService.shortenUrl(linkRequest.getLongLink());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating short link", e);
        }
    }
}
