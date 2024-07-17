package ru.arsentiev.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.arsentiev.dsl.LinkFilter;
import ru.arsentiev.dto.LinkRequest;
import ru.arsentiev.dto.LinkResponse;
import ru.arsentiev.page.PageResponse;
import ru.arsentiev.service.LinkService;

@RestController
@RequestMapping("/api/links")
@RequiredArgsConstructor
@Tag(name = "Link")
public class LinkController {
    private final LinkService service;

    @PostMapping
    public ResponseEntity<Long> saveLink(
            @Valid @RequestBody LinkRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.save(request, authentication));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateLink(
            @PathVariable("id") Long id,
            @Valid @RequestBody LinkRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.update(id, request, authentication));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteLink(
            @PathVariable("id") Long id,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.delete(id, authentication));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LinkResponse> findLinkById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<LinkResponse>> findAllLink(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.findAll(page, size, authentication));
    }

    @GetMapping("/user")
    public ResponseEntity<PageResponse<LinkResponse>> findAllLinkByUser(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            Authentication authentication
    ) {
        return ResponseEntity.ok(service.findAllLinkByUser(page, size, authentication));
    }

    @GetMapping("/filter")
    public ResponseEntity<PageResponse<LinkResponse>> findAllLinkByFilter(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size,
            @RequestBody LinkFilter filter
    ) {
        return ResponseEntity.ok(service.findAllLinkByFilter(page, size, filter));
    }

    @GetMapping("/redirect/{shortLink}")
    public ResponseEntity<Void> redirectToLongLink(@PathVariable String shortLink) {
        String longLink = service.findLongLinkByShortLink(shortLink);
        return ResponseEntity.status(302)
                .header(HttpHeaders.LOCATION, longLink)
                .build();
    }

}
