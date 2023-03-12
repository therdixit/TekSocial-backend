package com.teksocial.application.controllers;

import com.teksocial.application.models.*;
import com.teksocial.application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/chats")
public class ChatController {
    @Autowired
    private ChatRepository chatRepository;
    @GetMapping("/{user}")
    public List<String>getChatsList(@PathVariable String user){
        List<Chat> chats=chatRepository.findByUser1OrUser2(user, user);
        List<String> users=new ArrayList<>();
        for(Chat chat:chats){
            if (!chat.getUser1().equals(user)){
              users.add(chat.getUser1());
            }
            if(chat.getUser2().equals(user)){
                users.add(chat.getUser2());
            }
        }
        return users;
    }
}
