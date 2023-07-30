package com.chat.gpt.back.messages.repository;

import com.chat.gpt.back.messages.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<Message, UUID> {
  long count();
}