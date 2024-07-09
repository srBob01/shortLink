package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.LinkReadDto;
import ru.arsentiev.entity.Link;

@Mapper(componentModel = "spring", uses = CategoryReadMapper.class)
public interface LinkReadMapper {
    LinkReadMapper INSTANCE = Mappers.getMapper(LinkReadMapper.class);

    @Mapping(source = "category.title", target = "titleCategory")
    LinkReadDto linkToDto(Link link);

    @Mapping(source = "titleCategory", target = "category.title")
    Link dtoToLink(LinkReadDto linkReadDto);
}
