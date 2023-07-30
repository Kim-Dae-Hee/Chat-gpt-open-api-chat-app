package com.chat.gpt.back.messages.service;

import java.util.List;

import org.springframework.http.HttpEntity;

import com.chat.gpt.back.messages.DTO.ChatGptRequestDTO;
import com.chat.gpt.back.messages.DTO.ChatGptResponseDTO;
import com.chat.gpt.back.messages.DTO.QuestionRequestDTO;
import com.chat.gpt.back.messages.entity.Message;

public interface MessageService {
  public List<Message> getMessages();
  
  public Message saveMessage(QuestionRequestDTO questionRequestDTO);

  public Message questionMessage(QuestionRequestDTO questionRequestDTO);

  public HttpEntity<ChatGptRequestDTO> httpEntity(ChatGptRequestDTO requestDto);

  public ChatGptResponseDTO getResponse(HttpEntity<ChatGptRequestDTO> chatGptRequestDtoHttpEntity);

}
