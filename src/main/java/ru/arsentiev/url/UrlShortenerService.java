package ru.arsentiev.url;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.arsentiev.repository.LinkRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private static final Integer DEFAULT_LENGTH = 7;

    private final LinkRepository linkRepository;

    public String shortenUrl(String longUrl) throws NoSuchAlgorithmException {
        int attempt = linkRepository.countByLongLink(longUrl);

        String shortUrl;
        do {
            shortUrl = generateShortUrl(longUrl, attempt);
            attempt++;
        } while (linkRepository.existsByShortLink(shortUrl));

        return shortUrl;
    }

    private String generateShortUrl(String url, int attempt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest((url + attempt).getBytes(StandardCharsets.UTF_8));
        return Base64.getUrlEncoder().withoutPadding().encodeToString(hash).substring(0, DEFAULT_LENGTH);
    }

}