package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ru.arsentiev.dto.LinkWriteDto;
import ru.arsentiev.entity.Link;
import ru.arsentiev.service.UrlShortenerService;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring", uses = CategoryReadMapper.class)
public abstract class LinkWriteMapper {
    @Autowired
    private UrlShortenerService urlShortenerService;

    @Mapping(source = "categoryDto", target = "category")
    @Mapping(target = "removeDate", expression = "java(calculateRemoveDate(linkWriteDto.getValidHours()))")
    @Mapping(target = "shortLink", expression = "java(generateShortLink(linkWriteDto))")
    public abstract Link dtoToLink(LinkWriteDto linkWriteDto);

    public Link dtoToLink(LinkWriteDto linkWriteDto, Long idLink) {
        Link link = dtoToLink(linkWriteDto);
        link.setId(idLink);
        return link;
    }

    protected LocalDateTime calculateRemoveDate(int validHours) {
        return LocalDateTime.now().plusHours(validHours);
    }

    protected String generateShortLink(LinkWriteDto linkWriteDto) {
        try {
            return urlShortenerService.shortenUrl(linkWriteDto.getLongLink());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating short link", e);
        }
    }
}
