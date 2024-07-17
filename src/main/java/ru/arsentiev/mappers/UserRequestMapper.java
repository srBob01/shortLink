package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.arsentiev.dto.UserRequest;
import ru.arsentiev.entity.User;

@Mapper(componentModel = "spring")
public interface UserRequestMapper {
    void updateUserFromDto(UserRequest userRequest, @MappingTarget User user);
}