package com.chat.gpt.back.messages.DTO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChatGptResponseDTO implements Serializable {

    private String id;
    private String object;
    private LocalDate created;
    private String model;
    private List<Choice> choices;
    
    public ChatGptResponseDTO(String id, String object, LocalDate created, String model, List<Choice> choices) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
    }
}