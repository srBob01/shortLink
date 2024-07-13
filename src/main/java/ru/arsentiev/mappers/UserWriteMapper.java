package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;
import ru.arsentiev.dto.UserWriteDto;
import ru.arsentiev.entity.User;

@Mapper(componentModel = "spring")
public interface UserWriteMapper {
    UserWriteMapper INSTANCE = Mappers.getMapper(UserWriteMapper.class);

    User dtoToUser(UserWriteDto userWriteDto);

    default User dtoToUser(UserWriteDto userWriteDto, User user) {
        user.setEmail(userWriteDto.getEmail());
        user.setUsername(userWriteDto.getUsername());
        user.setFirstName(userWriteDto.getFirstName());
        user.setLastName(userWriteDto.getLastName());
        return user;
    }
}
