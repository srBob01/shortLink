package ru.arsentiev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.arsentiev.dsl.QPredicates;
import ru.arsentiev.dsl.UserFilter;
import ru.arsentiev.dto.UserPasDto;
import ru.arsentiev.dto.UserReadDto;
import ru.arsentiev.dto.UserWriteDto;
import ru.arsentiev.entity.Role;
import ru.arsentiev.mappers.UserPasMapper;
import ru.arsentiev.mappers.UserReadMapper;
import ru.arsentiev.mappers.UserWriteMapper;
import ru.arsentiev.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static ru.arsentiev.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private UserRepository userRepository;
    private UserWriteMapper userWriteMapper;
    private UserPasMapper userPasMapper;
    private UserReadMapper userReadMapper;

    public Optional<UserReadDto> findUserById(Integer id) {
        return userRepository.findById(id)
                .map(userReadMapper::userToDto);
    }

    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
        var predicate = QPredicates.builder()
                .add(filter.firstName(), user.firstName::containsIgnoreCase)
                .add(filter.lastName(), user.lastName::containsIgnoreCase)
                .buildAnd();

        return userRepository.findAll(predicate, pageable)
                .map(userReadMapper::userToDto);
    }

    Optional<UserPasDto> findUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .map(userPasMapper::userToDto);
    }

    List<UserReadDto> findAllByRoleOrderByUsernameDesc(Role role) {
        return userRepository.findAllByRoleOrderByUsernameDesc(role).stream()
                .map(userReadMapper::userToDto)
                .toList();
    }

    @Transactional
    public UserReadDto create(UserWriteDto user) {
        return Optional.of(user)
                .map(userWriteMapper::dtoToUser)
                .map(userRepository::save)
                .map(userReadMapper::userToDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<UserReadDto> update(Integer id, UserWriteDto userDto) {
        return userRepository.findById(id)
                .map(user -> userWriteMapper.dtoToUser(userDto, user))
                .map(userRepository::saveAndFlush)
                .map(userReadMapper::userToDto);
    }

    @Transactional
    public boolean delete(Integer id) {
        return userRepository.findById(id)
                .map(user -> {
                    userRepository.delete(user);
                    userRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
