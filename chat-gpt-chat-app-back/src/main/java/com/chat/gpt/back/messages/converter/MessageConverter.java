package com.chat.gpt.back.messages.converter;

import com.chat.gpt.back.messages.DTO.MessageDTO;
import com.chat.gpt.back.messages.entity.Message;

import org.springframework.stereotype.Component;

@Component
public class MessageConverter {

    public Message convertToEntity(MessageDTO messageModelDTO) {
        Message message = new Message();
        message.setId(messageModelDTO.getId());
        message.setText(messageModelDTO.getText());
        message.setSender(messageModelDTO.getSender());
        message.setCreatedDate(messageModelDTO.getCreatedDate());
        return message;
    }

    public MessageDTO convertToDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setText(message.getText());
        messageDTO.setSender(message.getSender());
        messageDTO.setCreatedDate(message.getCreatedDate());
        return messageDTO;
    }
}