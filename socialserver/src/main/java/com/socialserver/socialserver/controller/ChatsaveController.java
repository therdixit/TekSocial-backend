package com.socialserver.socialserver.controller;

import com.socialserver.socialserver.modal.ChatMessage;
import com.socialserver.socialserver.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChatsaveController {

    @Autowired
    private ChatMessageService chatMessageService;

    @PostMapping("/chat")
    public ChatMessage sendMessage(@RequestBody ChatMessage chatMessage) {
        return chatMessageService.save(chatMessage);
    }

}