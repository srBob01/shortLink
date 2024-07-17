package ru.arsentiev.service;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.arsentiev.configuration.JwtService;
import ru.arsentiev.dto.AuthenticationResponse;
import ru.arsentiev.dto.UserAuthenticationRequest;
import ru.arsentiev.dto.UserRegisterRequest;
import ru.arsentiev.email.EmailService;
import ru.arsentiev.email.EmailTemplateName;
import ru.arsentiev.entity.Token;
import ru.arsentiev.entity.User;
import ru.arsentiev.mappers.UserRegisterRequestMapper;
import ru.arsentiev.repository.TokenRepository;
import ru.arsentiev.repository.UserRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;


@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRegisterRequestMapper userRegisterRequestMapper;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailService emailService;
    private final SecureRandom secureRandom;

    @Value("${application.mailing.length-code}")
    public int lengthCode;

    @Value("${application.mailing.valid-code-minute}")
    public int validCodeMinute;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(UserRegisterRequest request) throws MessagingException {
        log.info("Registering user with email: {}", request.getEmail());
        User user = userRegisterRequestMapper.reqToUser(request);
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        log.info("Sending validation email to user with email: {}", user.getEmail());
        var newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.getUsername(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Account activation"
        );
    }

    private String generateAndSaveActivationToken(User user) {
        log.info("Generating activation token for user with email: {}", user.getEmail());
        String generatedToken;
        do {
            generatedToken = generateActivationCode(lengthCode);
        } while (tokenRepository.existsByToken(generatedToken));

        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(validCodeMinute))
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public AuthenticationResponse authenticate(UserAuthenticationRequest request) {
        log.info("Authenticating user with email: {}", request.getEmail());
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
                )
        );
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("email", user.getEmail());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void activateAccount(String token) throws MessagingException {
        log.info("Activating account with token: {}", token);
        Token savedToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> {
                    log.error("Invalid token: {}", token);
                    return new RuntimeException("Invalid token");
                });

        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            log.warn("Activation token has expired: {}", token);
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been sent to the email");
        }

        var user = userRepository.findById(savedToken.getUser().getId())
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", savedToken.getUser().getId());
                    return new UsernameNotFoundException("User " + savedToken.getUser().getId() + " not found");
                });

        user.setEnabled(true);
        userRepository.saveAndFlush(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.saveAndFlush(savedToken);
        log.info("Account activated for user with email: {}", user.getEmail());
    }
}
