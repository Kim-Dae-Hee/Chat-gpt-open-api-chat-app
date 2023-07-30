package com.chat.gpt.back.messages.service;

import java.util.Date;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.chat.gpt.back.configuration.ChatGptConfig;
import com.chat.gpt.back.messages.DTO.ChatGptRequestDTO;
import com.chat.gpt.back.messages.DTO.ChatGptResponseDTO;
import com.chat.gpt.back.messages.DTO.Choice;
import com.chat.gpt.back.messages.DTO.QuestionRequestDTO;
import com.chat.gpt.back.messages.entity.Message;
import com.chat.gpt.back.messages.repository.MessageRepository;

@Service
public class MessageServiceImp implements MessageService {

  private final MessageRepository messageRepository;
  private RestTemplate restTemplate = new RestTemplate();

  public MessageServiceImp(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @Override
  public List<Message> getMessages() {
    return messageRepository.findAll();
  }

  @Override
  public HttpEntity<ChatGptRequestDTO> httpEntity(ChatGptRequestDTO request) {
    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);

        return new HttpEntity<>(request, headers);
  }

  @Override
  public ChatGptResponseDTO getResponse(HttpEntity<ChatGptRequestDTO> chatGptRequest) {
    ResponseEntity<ChatGptResponseDTO> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequest,
                ChatGptResponseDTO.class);

        return responseEntity.getBody();
  }  

 @Override
  public Message saveMessage(QuestionRequestDTO questionRequestDTO) {
    Message newMessage = createNewMessage(questionRequestDTO.getQuestion(), "user");

    return messageRepository.save(newMessage);
  }

  @Override
  public Message questionMessage(QuestionRequestDTO questionRequestDTO) {
    //여기에 chat gpt open api로부터 응답을 가져오는 부분 구현
    ChatGptResponseDTO response = getResponse(
       httpEntity(
              ChatGptRequestDTO.builder()
              .model(ChatGptConfig.MODEL)
              .prompt(questionRequestDTO.getQuestion())
              .maxTokens(ChatGptConfig.MAX_TOKEN)
              .temperature(ChatGptConfig.TEMPERATURE)
              .topP(ChatGptConfig.TOP_P)
              .build()
      )
    );
    
    // 응답에서 메시지를 꺼내는 부분
    List<Choice> choices = response.getChoices();
    StringBuilder responseTextBuilder = new StringBuilder();
    for (Choice choice : choices) {
      String text = choice.getText();
      responseTextBuilder.append(text).append("\n");
    }

    Message responseMessage = createNewMessage(responseTextBuilder.toString(), "bot");
    
    // chat gpt api는 사용 횟수가 한계가 있어서 테트할 때 사용하는 코드
    // try {
    // Thread.sleep(5000);
    // } catch(Exception e) {
    //   e.printStackTrace();
    // }
    // Message responseMessage = createNewMessage("응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트응답테스트", "bot");

    return messageRepository.save(responseMessage);
  }

  private Message createNewMessage(String text, String senderType) {
    Message newMessage = new Message();
    // newMessage.setId(UUID.randomUUID());
    newMessage.setText(text);
    newMessage.setSender(senderType);
    newMessage.setCreatedDate(new Date());

    return newMessage;
  }

}
