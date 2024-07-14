package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import ru.arsentiev.dto.UserWriteDto;
import ru.arsentiev.entity.User;

@Mapper(componentModel = "spring")
public interface UserWriteMapper {
    User dtoToUser(UserWriteDto userWriteDto);

    default User dtoToUser(UserWriteDto userWriteDto, Integer idUser, String password) {
        User user = dtoToUser(userWriteDto);
        user.setId(idUser);
        user.setPassword(password);
        return user;
    }
}
