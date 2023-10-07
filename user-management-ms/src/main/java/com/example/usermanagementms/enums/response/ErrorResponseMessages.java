package com.example.usermanagementms.enums.response;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
public enum ErrorResponseMessages implements ResponseMessage {
    UNEXPECTED("unexpected", "Unexpected error", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND("not_found_%s", "%s can't find %s", HttpStatus.NOT_FOUND),
    EMAIL_ALREADY_REGISTERED("email_already_registered", "Email already registered", HttpStatus.CONFLICT),
    PHONE_NUMBER_ALREADY_EXIST("phone_number_already_exist", "Phone number already exist", HttpStatus.CONFLICT),
    FORBIDDEN("forbidden","Forbidden", HttpStatus.FORBIDDEN),
    USER_NOT_ACTIVE("user_not_active", "User is not active", HttpStatus.FORBIDDEN);

    private final String key;
    private final String message;
    private final HttpStatus status;
    @Override
    public String key() {
        return null;
    }

    @Override
    public String message() {
        return null;
    }

    @Override
    public HttpStatus status() {
        return null;
    }
}
