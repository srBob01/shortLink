package ru.arsentiev.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.arsentiev.dsl.UserFilter;
import ru.arsentiev.dto.UserRequest;
import ru.arsentiev.dto.UserResponse;
import ru.arsentiev.page.PageResponse;
import ru.arsentiev.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN') or #id == principal.id")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable("id") Integer id,
            @RequestBody @Valid UserRequest userRequest,
            Authentication authentication
    ) {
        return ResponseEntity.ok(userService.updateUser(id, userRequest, authentication));
    }

    @GetMapping
    public ResponseEntity<PageResponse<UserResponse>> findAllUsers(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {
        return ResponseEntity.ok(userService.findAllUsers(page, size));
    }

    @GetMapping("/filter")
    public ResponseEntity<PageResponse<UserResponse>> findAllUsersByFilter(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestBody @Valid UserFilter filter
    ) {
        return ResponseEntity.ok(userService.findAllUserByFilter(page, size, filter));
    }
}
