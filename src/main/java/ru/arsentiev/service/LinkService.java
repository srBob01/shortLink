package ru.arsentiev.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.arsentiev.dsl.LinkFilter;
import ru.arsentiev.dsl.QPredicates;
import ru.arsentiev.dto.LinkRequest;
import ru.arsentiev.dto.LinkResponse;
import ru.arsentiev.entity.Link;
import ru.arsentiev.entity.Role;
import ru.arsentiev.entity.User;
import ru.arsentiev.mappers.LinkRequestMapper;
import ru.arsentiev.mappers.LinkResponseMapper;
import ru.arsentiev.page.PageResponse;
import ru.arsentiev.repository.LinkRepository;

import java.util.List;

import static ru.arsentiev.dsl.LinkSpecification.withUserId;
import static ru.arsentiev.entity.QCategory.category;
import static ru.arsentiev.entity.QLink.link;
import static ru.arsentiev.entity.QUser.user;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LinkService {
    private final LinkRepository linkRepository;
    private final LinkResponseMapper linkResponseMapper;
    private final LinkRequestMapper linkRequestMapper;

    @Transactional
    public Long save(LinkRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        Link link = linkRequestMapper.dtoToLink(request, user);
        log.info("Saving link: {}", link);
        return linkRepository.save(link).getId();
    }

    public LinkResponse findById(Long id) {
        log.info("Finding link by id: {}", id);
        return linkRepository.findById(id)
                .map(linkResponseMapper::linkToDto)
                .orElseThrow(() -> {
                    log.error("No link found with id: {}", id);
                    return new EntityNotFoundException("No link with id: " + id);
                });
    }

    @Transactional
    public Long update(Long id, LinkRequest request, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info("Updating link with id: {}", id);
        Link link = linkRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No link found with id: {}", id);
                    return new EntityNotFoundException("No link with id: " + id);
                });
        if (link.getUser().getId().equals(user.getId()) || user.getRole().equals(Role.ADMIN)) {
            linkRequestMapper.updateLinkFromDto(request, link);
            linkRepository.saveAndFlush(link);
            log.info("Link updated: {}", link);
        } else {
            log.error("User is not authorized to update this link. User ID: {}, Link ID: {}", user.getId(), id);
            throw new SecurityException("User is not authorized to update this link");
        }
        return id;
    }

    @Transactional
    public Long delete(Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info("Deleting link with id: {}", id);
        Link link = linkRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("No link found with id: {}", id);
                    return new EntityNotFoundException("No link with id: " + id);
                });
        if (link.getUser().getId().equals(user.getId()) || user.getRole().equals(Role.ADMIN)) {
            linkRepository.delete(link);
            linkRepository.flush();
            log.info("Link deleted: {}", link);
        } else {
            log.error("User is not authorized to delete this link. User ID: {}, Link ID: {}", user.getId(), id);
            throw new SecurityException("User is not authorized to delete this link");
        }
        return id;
    }

    public PageResponse<LinkResponse> findAll(int page, int size, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info("Finding all links for user with id: {}", user.getId());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Link> links = linkRepository.findAllDisplayedLinks(pageable, user.getId());
        return createPageResponse(links);
    }

    public PageResponse<LinkResponse> findAllLinkByUser(int page, int size, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        log.info("Finding all links by user with id: {}", user.getId());
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<Link> links = linkRepository.findAll(withUserId(user.getId()), pageable);
        return createPageResponse(links);
    }

    public PageResponse<LinkResponse> findAllLinkByFilter(int page, int size, LinkFilter filter) {
        log.info("Finding all links by filter: {}", filter);
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        var predicate = QPredicates.builder()
                .add(filter.getUsername(), user.username::equalsIgnoreCase)
                .add(filter.getEmail(), user.email::containsIgnoreCase)
                .add(filter.getTitleCategory(), category.title::equalsIgnoreCase)
                .add(filter.getLinkName(), link.linkName::equalsIgnoreCase)
                .buildAnd();
        Page<Link> links = linkRepository.findAll(predicate, pageable);
        return createPageResponse(links);
    }

    public String findLongLinkByShortLink(String shortLink) {
        log.info("Finding long link by short link: {}", shortLink);
        return linkRepository.findByShortLink(shortLink)
                .map(Link::getLongLink)
                .orElseThrow(() -> {
                    log.error("No link found for short link: {}", shortLink);
                    return new EntityNotFoundException("No link found for short link: " + shortLink);
                });
    }

    private PageResponse<LinkResponse> createPageResponse(Page<Link> links) {
        List<LinkResponse> linkResponse = links.stream()
                .map(linkResponseMapper::linkToDto)
                .toList();
        return new PageResponse<>(
                linkResponse,
                links.getNumber(),
                links.getSize(),
                links.getTotalElements(),
                links.getTotalPages(),
                links.isFirst(),
                links.isLast());
    }
}
