package ru.arsentiev.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.arsentiev.dto.UserRegisterRequest;
import ru.arsentiev.entity.Role;
import ru.arsentiev.entity.User;

@Mapper(componentModel = "spring", imports = {Role.class})
public abstract class UserRegisterRequestMapper {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Mapping(target = "password", source = "password", qualifiedByName = "encodePassword")
    @Mapping(target = "role", expression = "java(Role.USER)")
    public abstract User reqToUser(UserRegisterRequest request);

    @Named("encodePassword")
    protected String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}