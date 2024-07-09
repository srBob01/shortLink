package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.UserLogPasDto;
import ru.arsentiev.entity.User;

@Mapper(componentModel = "spring")
public interface UserLogPasMapper {
    UserLogPasMapper INSTANCE = Mappers.getMapper(UserLogPasMapper.class);

    UserLogPasDto userToDto(User user);

}
