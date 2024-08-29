package com.example.sms.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class MessageDTO {
    private UUID id;
    private String phoneNumber;
    private boolean isWhatsApp;
    private String text;
    private CustomerDTO customer;
    private LocalDateTime createdAt;
}
