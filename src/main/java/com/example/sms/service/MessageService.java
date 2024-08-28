package com.example.sms.service;

import com.example.sms.entity.Message;
import com.example.sms.repository.MessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor(onConstructor_ = @__(@Autowired))
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Message send(Message message) {
        return messageRepository.save(message);
    }
}