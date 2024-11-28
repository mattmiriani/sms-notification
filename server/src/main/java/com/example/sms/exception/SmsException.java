package com.example.sms.exception;

import org.springframework.http.HttpStatus;

import static java.util.Objects.isNull;

public class SmsException extends RuntimeException {
    private final HttpStatus status;

    public SmsException(HttpStatus status, String message) {
        super(message);

        this.status = status;
    }

    public HttpStatus getStatus() {
        if (isNull(status)) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return status;
    }
}
