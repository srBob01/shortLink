package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.LinkWriteDto;
import ru.arsentiev.dto.UserWriteDto;
import ru.arsentiev.entity.Category;
import ru.arsentiev.entity.Link;
import ru.arsentiev.entity.User;

@Mapper(componentModel = "spring", uses = CategoryReadMapper.class)
public interface LinkWriteMapper {
    LinkWriteMapper INSTANCE = Mappers.getMapper(LinkWriteMapper.class);

    @Mapping(source = "categoryDto", target = "category")
    Link dtoToLink(LinkWriteDto linkWriteDto);

    default Link dtoToLink(LinkWriteDto linkWriteDto, Link link) {
        link.setLongLink(linkWriteDto.getLongLink());
        link.setShortLink(linkWriteDto.getShortLink());
        link.setCategory(Category.builder()
                .id(linkWriteDto.getCategoryDto().getId())
                .title(linkWriteDto.getCategoryDto().getTitle())
                .build());
        return link;
    }
}
