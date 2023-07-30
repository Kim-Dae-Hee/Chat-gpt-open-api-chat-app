package com.chat.gpt.back.messages.DTO;

import java.util.Date;
import java.util.UUID;
import lombok.Data;

@Data
public class MessageDTO {
  private UUID id;
  private String text;
  private String sender;
  private Date createdDate;
}
