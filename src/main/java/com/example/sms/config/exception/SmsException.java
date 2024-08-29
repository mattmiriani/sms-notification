package com.example.sms.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SmsException extends RuntimeException {
    private final HttpStatus status;

    public SmsException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
