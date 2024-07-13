package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.UserLinkReadDto;
import ru.arsentiev.entity.UserLink;

@Mapper(componentModel = "spring", uses = {UserReadMapper.class, LinkReadMapper.class})
public interface UserLinkReadMapper {
    UserLinkReadMapper INSTANCE = Mappers.getMapper(UserLinkReadMapper.class);

    @Mapping(source = "user.id", target = "userDto.id")
    @Mapping(source = "link.id", target = "linkDto.id")
    UserLinkReadDto userLinkToDto(UserLink userLink);
}
