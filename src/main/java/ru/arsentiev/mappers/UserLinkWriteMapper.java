package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.UserLinkWriteDto;
import ru.arsentiev.entity.UserLink;

@Mapper(componentModel = "spring", uses = {UserReadMapper.class, LinkReadMapper.class})
public interface UserLinkWriteMapper {
    UserLinkWriteMapper INSTANCE = Mappers.getMapper(UserLinkWriteMapper.class);

    @Mapping(source = "userDto.id", target = "user.id")
    @Mapping(source = "linkDto.id", target = "link.id")
    UserLink dtoToUserLink(UserLinkWriteDto userLinkWriteDto);
}