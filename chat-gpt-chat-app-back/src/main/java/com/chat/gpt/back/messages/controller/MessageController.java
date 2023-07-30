package com.chat.gpt.back.messages.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chat.gpt.back.messages.service.MessageService;
import com.chat.gpt.back.messages.DTO.MessageDTO;
import com.chat.gpt.back.messages.DTO.QuestionRequestDTO;
import com.chat.gpt.back.messages.converter.MessageConverter;
import com.chat.gpt.back.messages.entity.Message;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class MessageController {

    private final MessageService messageService;
    private final MessageConverter messageConverter;

    public MessageController(MessageService messageService, MessageConverter messageConverter) {
        this.messageService = messageService;
        this.messageConverter = messageConverter;
    }

    // GET 요청 처리
    @GetMapping("/messages")
    public ResponseEntity<List<MessageDTO>> getMessages() {
        List<Message> messages = messageService.getMessages();
        List<MessageDTO> messageModels = messages.stream()
                .map(messageConverter::convertToDTO)
                //.map(message -> messageConverter.convertToModel(message))
                .collect(Collectors.toList());

        return ResponseEntity.ok(messageModels);
    }

    @PostMapping("/messages/save")
    public ResponseEntity<MessageDTO> saveMessage(@RequestBody QuestionRequestDTO questionRequestDTO) {
        Message reponseMessage = messageService.saveMessage(questionRequestDTO);
        MessageDTO reponseMessageDTO = messageConverter.convertToDTO(reponseMessage);

        return ResponseEntity.ok(reponseMessageDTO);
    }

    // POST 요청 처리
    @PostMapping("/messages")
    public ResponseEntity<MessageDTO> questionMessage(@RequestBody QuestionRequestDTO questionRequestDTO) {
        Message reponseMessage = messageService.questionMessage(questionRequestDTO);
        MessageDTO reponseMessageDTO = messageConverter.convertToDTO(reponseMessage);

        return ResponseEntity.ok(reponseMessageDTO);
    }
}