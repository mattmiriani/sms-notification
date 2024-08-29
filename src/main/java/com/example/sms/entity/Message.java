package com.example.sms.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity(name = "message")
public class Message implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "is_whatsapp", nullable = false)
    private boolean isWhatsApp;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Message(Message message) {
        this.phoneNumber = message.getPhoneNumber();
        this.isWhatsApp = message.isWhatsApp();
        this.text = message.getText();
        this.createdAt = LocalDateTime.now();
        this.customer = message.getCustomer();
    }
}
