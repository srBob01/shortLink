package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import ru.arsentiev.dto.UserResponse;
import ru.arsentiev.entity.User;

@Mapper(componentModel = "spring")
public interface UserResponseMapper {
    UserResponse userToDto(User user);
}

