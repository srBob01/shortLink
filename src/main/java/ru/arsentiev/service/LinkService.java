package ru.arsentiev.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.arsentiev.dto.LinkReadDto;
import ru.arsentiev.dto.LinkWriteDto;
import ru.arsentiev.mappers.LinkReadMapper;
import ru.arsentiev.mappers.LinkWriteMapper;
import ru.arsentiev.repository.LinkRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LinkService {
    private final LinkRepository linkRepository;
    private final LinkReadMapper linkReadMapper;
    private final LinkWriteMapper linkWriteMapper;

    @Transactional
    public LinkReadDto create(LinkWriteDto link) {
        return Optional.of(link)
                .map(linkWriteMapper::dtoToLink)
                .map(linkRepository::save)
                .map(linkReadMapper::linkToDto)
                .orElseThrow();
    }

    @Transactional
    public Optional<LinkReadDto> update(Long id, LinkWriteDto linkDto) {
        return linkRepository.findById(id)
                .map(link -> linkWriteMapper.dtoToLink(linkDto, link.getId()))
                .map(linkRepository::saveAndFlush)
                .map(linkReadMapper::linkToDto);
    }

    @Transactional
    public boolean delete(Long id) {
        return linkRepository.findById(id)
                .map(user -> {
                    linkRepository.delete(user);
                    linkRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
