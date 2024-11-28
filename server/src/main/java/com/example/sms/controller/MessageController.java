package com.example.sms.controller;

import com.example.sms.exception.SmsException;
import com.example.sms.dto.MessageDTO;
import com.example.sms.mapper.MessageMapper;
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
public class MessageController extends ControllerDefault {

    private final MessageService messageService;
    private final MessageMapper messageMapper;

    @PostMapping("/send")
    public ResponseEntity<MessageDTO> send(@RequestBody MessageDTO messageDTO) {
        try {
            var message = messageMapper.messageDTOToMessage(messageDTO);
            var response = messageService.send(message);

            return ResponseEntity.ok(messageMapper.messageToMessageDTO(response));
        } catch (SmsException e) {

            throw new SmsException(e.getStatus(), e.getMessage());
        }
    }
}
