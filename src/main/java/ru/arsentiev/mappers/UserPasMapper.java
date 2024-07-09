package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.UserPasDto;
import ru.arsentiev.entity.User;

@Mapper(componentModel = "spring")
public interface UserPasMapper {
    UserPasMapper INSTANCE = Mappers.getMapper(UserPasMapper.class);

    UserPasDto userToDto(User user);

}
