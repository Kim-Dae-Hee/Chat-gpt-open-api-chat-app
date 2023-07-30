package com.chat.gpt.back.messages.DTO;

import java.io.Serializable;
import lombok.Data;

@Data
public class QuestionRequestDTO implements Serializable {
  private String question;
}