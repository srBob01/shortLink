package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import ru.arsentiev.dto.UserPasDto;
import ru.arsentiev.entity.User;

@Mapper(componentModel = "spring")
public interface UserPasMapper {
    UserPasDto userToDto(User user);

    User dtoToUser(UserPasDto user);

}
