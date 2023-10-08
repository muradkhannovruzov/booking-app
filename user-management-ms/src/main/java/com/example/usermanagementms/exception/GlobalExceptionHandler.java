package com.example.usermanagementms.exception;


import com.example.usermanagementms.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<?>> handleBaseException(BaseException ex) {
        log.info("handleBaseException", ex);
        HttpStatus status = ex.getResponseMessage().status();
        return ResponseEntity.status(status).body(BaseResponse.error(ex));
    }
}
