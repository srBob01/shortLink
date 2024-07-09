package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.arsentiev.dto.UserWriteDto;
import ru.arsentiev.entity.User;

@Mapper(componentModel = "spring")
public interface UserWriteMapper {
    UserWriteMapper INSTANCE = Mappers.getMapper(UserWriteMapper.class);
    User dtoToUser(UserWriteDto userWriteDto);
}
