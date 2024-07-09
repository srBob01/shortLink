package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.LinkWriteDto;
import ru.arsentiev.entity.Link;

@Mapper(componentModel = "spring", uses = CategoryReadMapper.class)
public interface LinkWriteMapper {
    LinkWriteMapper INSTANCE = Mappers.getMapper(LinkWriteMapper.class);

    @Mapping(source = "categoryDto", target = "category")
    Link dtoToLink(LinkWriteDto linkWriteDto);
}
