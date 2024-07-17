package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.arsentiev.dto.LinkResponse;
import ru.arsentiev.entity.Link;

@Mapper(componentModel = "spring")
public interface LinkResponseMapper {
    @Mapping(source = "category.title", target = "titleCategory")
    @Mapping(source = "user.username", target = "username")
    LinkResponse linkToDto(Link link);
}
