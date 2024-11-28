package com.example.sms.controller;

import com.example.sms.exception.SmsException;
import com.example.sms.dto.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;

@CrossOrigin(origins = "http://localhost:5173")
public class ControllerDefault {

    @ExceptionHandler(SmsException.class)
    public ResponseEntity<ErrorDTO> handleSmsException(SmsException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorDTO(ex.getMessage()));
    }
}
