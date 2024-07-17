package ru.arsentiev.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.arsentiev.dsl.QPredicates;
import ru.arsentiev.dsl.UserFilter;
import ru.arsentiev.dto.UserRequest;
import ru.arsentiev.dto.UserResponse;
import ru.arsentiev.entity.User;
import ru.arsentiev.mappers.UserRequestMapper;
import ru.arsentiev.mappers.UserResponseMapper;
import ru.arsentiev.page.PageResponse;
import ru.arsentiev.repository.UserRepository;

import java.util.List;

import static ru.arsentiev.entity.QUser.user;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserRequestMapper userRequestMapper;
    private final UserResponseMapper userResponseMapper;

    public UserResponse findUserById(Integer id) {
        return userRepository.findById(id)
                .map(userResponseMapper::userToDto)
                .orElseThrow(() -> new EntityNotFoundException("No user with id:" + id));
    }

    @Transactional
    public void deleteUserById(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("No user with id:" + id);
        }
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResponse updateUser(Integer id, UserRequest userRequest, Authentication authentication) {
        return userRepository.findById(id)
                .map(existingUser -> {
                    userRequestMapper.updateUserFromDto(userRequest, existingUser);
                    userRepository.saveAndFlush(existingUser);
                    return userResponseMapper.userToDto(existingUser);
                })
                .orElseThrow(() -> new EntityNotFoundException("No user with id:" + id));
    }

    private PageResponse<UserResponse> createPageResponse(Page<User> users) {
        List<UserResponse> linkResponse = users.stream()
                .map(userResponseMapper::userToDto)
                .toList();
        return new PageResponse<>(
                linkResponse,
                users.getNumber(),
                users.getSize(),
                users.getTotalElements(),
                users.getTotalPages(),
                users.isFirst(),
                users.isLast());
    }

    public PageResponse<UserResponse> findAllUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users = userRepository.findAll(pageable);
        return createPageResponse(users);
    }

    public PageResponse<UserResponse> findAllUserByFilter(int page, int size, UserFilter filter) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        var predicate = QPredicates.builder()
                .add(filter.getFirstName(), user.firstName::equalsIgnoreCase)
                .add(filter.getLastName(), user.lastName::equalsIgnoreCase)
                .add(filter.getEmail(), user.email::containsIgnoreCase)
                .add(filter.getUsername(), user.username::equalsIgnoreCase)
                .buildAnd();
        Page<User> users = userRepository.findAll(predicate, pageable);
        return createPageResponse(users);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Failed to retrieve user:" + username));
    }
}
