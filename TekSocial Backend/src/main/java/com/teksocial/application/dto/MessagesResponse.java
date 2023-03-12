package com.teksocial.application.dto;

import com.teksocial.application.models.ChatModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessagesResponse {
    private String status;
    private List<ChatModel> chatModelList;
}
