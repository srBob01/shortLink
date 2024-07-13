//package ru.arsentiev.service;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.arsentiev.dto.UserLinkReadDto;
//import ru.arsentiev.dto.UserReadDto;
//import ru.arsentiev.dto.UserWriteDto;
//import ru.arsentiev.entity.Role;
//import ru.arsentiev.mappers.UserLinkWriteMapper;
//import ru.arsentiev.repository.UserLinkRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional(readOnly = true)
//public class UserLinkService {
//    private final UserLinkRepository userLinkRepository;
//    private final UserLinkWriteMapper userLinkWriteMapper;
//
//    public List<UserLinkReadDto> findAll() {
//        return userLinkRepository.findAllIn();
//    }
//
//    public List<UserLinkReadDto> findAllByIDUser(Integer idUser) {
//        return userLinkRepository.findAllByIDUser(idUser);
//    }
//
//    public List<UserLinkReadDto> findAllByIDUser(Integer idUser) {
//        return userLinkRepository.findAllByIDUser(idUser);
//    }
//
//    @Transactional
//    public UserReadDto create(UserWriteDto user) {
//        return Optional.of(user)
//                .map(userWriteMapper::dtoToUser)
//                .map(userRepository::save)
//                .map(userReadMapper::userToDto)
//                .orElseThrow();
//    }
//
//    @Transactional
//    public Optional<UserReadDto> update(Integer id, UserWriteDto userDto) {
//        return userRepository.findById(id)
//                .map(user -> userWriteMapper.dtoToUser(userDto, user))
//                .map(userRepository::saveAndFlush)
//                .map(userReadMapper::userToDto);
//    }
//
//    @Transactional
//    public boolean delete(Integer id) {
//        return userRepository.findById(id)
//                .map(user -> {
//                    userRepository.delete(user);
//                    userRepository.flush();
//                    return true;
//                })
//                .orElse(false);
//    }
//}
