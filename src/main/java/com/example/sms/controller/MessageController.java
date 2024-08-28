package com.example.sms.controller;

import com.example.sms.entity.Message;
import com.example.sms.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@RestController
@RequestMapping("/messages")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<Message> send(@RequestBody Message message) {
        try {
            return ResponseEntity.ok(messageService.send(message));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
