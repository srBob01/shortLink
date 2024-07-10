package ru.arsentiev.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.arsentiev.dsl.UserFilter;
import ru.arsentiev.dto.UserReadDto;
import ru.arsentiev.dto.UserWriteDto;
import ru.arsentiev.page.PageResponse;
import ru.arsentiev.service.UserService;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UserRestController {
    private UserService userService;

    @GetMapping
    public PageResponse<UserReadDto> findAll(UserFilter userFilter, Pageable pageable) {
        Page<UserReadDto> page = userService.findAll(userFilter, pageable);
        return PageResponse.of(page);
    }

    @GetMapping("/{id}")
    public UserReadDto findById(@PathVariable("id") Integer id) {
        return userService.findUserById(id)
                .orElseThrow();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto create(@Validated @RequestBody UserWriteDto user) {
        return userService.create(user);
    }

    @PutMapping("/{id}")
    public UserReadDto update(@PathVariable("id") Integer id,
                              @Validated @RequestBody UserWriteDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        if (!userService.delete(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

}
