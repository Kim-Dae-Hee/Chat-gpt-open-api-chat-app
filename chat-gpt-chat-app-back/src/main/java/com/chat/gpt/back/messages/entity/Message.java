package com.chat.gpt.back.messages.entity;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import lombok.Data;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "message_table")
@Data
public class Message {
  @Id
  @GeneratedValue(generator = "uuid2")
  @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;

  @Column(length=10000, nullable=true)
  private String text;

  @Column(length=10, nullable=true)
  private String sender;

  @Column(name = "created_date", nullable = true)
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;
}
