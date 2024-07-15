package ru.arsentiev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.arsentiev.entity.Token;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);
}
