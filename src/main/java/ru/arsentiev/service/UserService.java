package ru.arsentiev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.arsentiev.auth.RegisterRequest;
import ru.arsentiev.dto.UserReadDto;
import ru.arsentiev.dto.UserWriteDto;
import ru.arsentiev.entity.Role;
import ru.arsentiev.mappers.RegisterRequestMapper;
import ru.arsentiev.mappers.UserPasMapper;
import ru.arsentiev.mappers.UserReadMapper;
import ru.arsentiev.mappers.UserWriteMapper;
import ru.arsentiev.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserWriteMapper userWriteMapper;
    private final UserPasMapper userPasMapper;
    private final UserReadMapper userReadMapper;

    public Optional<UserReadDto> findUserById(Integer id) {
        return userRepository.findById(id)
                .map(userReadMapper::userToDto);
    }

//    public Page<UserReadDto> findAll(UserFilter filter, Pageable pageable) {
//        var predicate = QPredicates.builder()
//                .add(filter.firstName(), user.firstName::containsIgnoreCase)
//                .add(filter.lastName(), user.lastName::containsIgnoreCase)
//                .buildAnd();
//
//        return userRepository.findAll(predicate, pageable)
//                .map(userReadMapper::userToDto);
//    }

//    public Page<UserReadDto> findAll(Pageable pageable) {
//        return userRepository.findAll(pageable)
//                .map(userReadMapper::userToDto);
//    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::userToDto)
                .toList();
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
                .map(user -> userWriteMapper.dtoToUser(userDto, user.getId(), user.getPassword()))
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user:" + username));
    }
}
