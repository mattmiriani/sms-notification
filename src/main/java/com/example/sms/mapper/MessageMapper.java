package com.example.sms.mapper;

import com.example.sms.dto.MessageDTO;
import com.example.sms.entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    @Mapping(source = "customer", target = "customer")
    MessageDTO messageToMessageDTO(Message message);

    @Mapping(source = "customer", target = "customer")
    Message messageDTOToMessage(MessageDTO messageDTO);

}
