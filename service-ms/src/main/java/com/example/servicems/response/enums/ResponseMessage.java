package com.example.servicems.response.enums;

import org.springframework.http.HttpStatus;

public interface ResponseMessage {
    String key();
    String message();
    HttpStatus status();
}
