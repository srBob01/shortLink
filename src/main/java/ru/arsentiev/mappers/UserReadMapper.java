package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.UserReadDto;
import ru.arsentiev.entity.User;

@Mapper(componentModel = "spring")
public interface UserReadMapper {
    UserReadDto userToDto(User user);

    User dtoToUser(UserReadDto userReadDto);
}

