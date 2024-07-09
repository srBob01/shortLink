package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.LinkReadDto;
import ru.arsentiev.entity.Link;

@Mapper(componentModel = "spring", uses = CategoryReadMapper.class)
public interface LinkReadMapper {
    LinkReadMapper INSTANCE = Mappers.getMapper(LinkReadMapper.class);

    @Mapping(source = "category.id", target = "idCategory")
    LinkReadDto linkToDto(Link link);

    @Mapping(source = "idCategory", target = "category.id")
    Link dtoToLink(LinkReadDto linkReadDto);
}
