package ru.arsentiev.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum ErrorCode {
    NO_CODE(0, "No code", NOT_IMPLEMENTED),
    INCORRECT_CURRENT_PASSWORD(300, "Current password is incorrect ", BAD_REQUEST),
    INCORRECT_NEW_PASSWORD(301, "New password is incorrect ", BAD_REQUEST),
    ACCOUNT_LOCKED(302, "User is locked", FORBIDDEN),
    ACCOUNT_DISABLED(303, "User is disabled", FORBIDDEN),
    BAD_CREDENTIALS(304, "Login and / or password is incorrect", FORBIDDEN),
    USER_NOT_AUTHORIZED(305, "User is not authorized to perform this action", FORBIDDEN),
    ENTITY_NOT_FOUND(306, "Entity not found", NOT_FOUND),
    USERNAME_NOT_FOUND(307, "Username not found", NOT_FOUND);
    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    ErrorCode(int code, String description, HttpStatus httpStatus) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
