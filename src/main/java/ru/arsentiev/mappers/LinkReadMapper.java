package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.arsentiev.dto.LinkReadDto;
import ru.arsentiev.entity.Link;

@Mapper(componentModel = "spring", uses = CategoryReadMapper.class)
public interface LinkReadMapper {
    @Mapping(source = "category.title", target = "titleCategory")
    @Mapping(source = "user.username", target = "username")
    LinkReadDto linkToDto(Link link);

    @Mapping(source = "titleCategory", target = "category.title")
    @Mapping(source = "username", target = "user.username")
    Link dtoToLink(LinkReadDto linkReadDto);
}
