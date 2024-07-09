package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.UserLinkReadDto;
import ru.arsentiev.entity.UserLink;

@Mapper(componentModel = "spring")
public interface UserLinkReadMapper {
    UserLinkReadMapper INSTANCE = Mappers.getMapper(UserLinkReadMapper.class);

    @Mapping(source = "user.id", target = "idUser")
    @Mapping(source = "link.id", target = "idLink")
    UserLinkReadDto userLinkToDto(UserLink userLink);

    @Mapping(source = "idUser", target = "user.id")
    @Mapping(source = "idLink", target = "link.id")
    UserLink dtoToUserLink(UserLinkReadDto userLinkReadDto);
}