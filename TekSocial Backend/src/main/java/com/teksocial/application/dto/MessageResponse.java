package com.teksocial.application.dto;

import com.teksocial.application.models.ChatModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MessageResponse {
    private String status;
    private ChatModel chatModel;
}

