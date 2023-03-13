package com.teksocial.application.controllers;

import com.teksocial.application.dto.MessageResponse;
import com.teksocial.application.dto.MessagesResponse;
import com.teksocial.application.models.*;
import com.teksocial.application.repositories.*;
import com.teksocial.application.services.ChatRoomService;
import com.teksocial.application.services.ChatService;
import com.teksocial.application.utility.GlobalUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:8081/", allowCredentials = "true")
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private ChatService chatService;
    
    
    @MessageMapping("/chat")
    public void processMessage(@Payload ChatModel chatMessage) {
        System.out.println(chatMessage);
        var chatId = chatRoomService
                .getChatId(chatMessage.getSenderId(), chatMessage.getRecipientId(), true);
        chatMessage.setChatId(chatId.get());

        ChatModel saved = chatService.save(chatMessage);
        messagingTemplate.convertAndSendToUser(
                chatMessage.getRecipientId().toString(),"/queue/messages",
                new ChatNotificationModel(
                        saved.getId(),
                        saved.getSenderId()
                        ));
    }

    @GetMapping("/messages/{senderId}/{recipientId}/count")
    public ResponseEntity<Long> countNewMessages(
            @PathVariable Long senderId,
            @PathVariable Long recipientId) {

        return ResponseEntity
                .ok(chatService.countNewMessages(senderId, recipientId));
    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<?> findChatMessages (@PathVariable Long senderId,
                                               @PathVariable Long recipientId) {
        System.out.println("This API has been called");
        MessagesResponse messagesResponse = new MessagesResponse();
        messagesResponse.setChatModelList(chatService.findChatMessages(senderId, recipientId));
        messagesResponse.setStatus(GlobalUtilities.API_SUCCESS_STATUS);
        return ResponseEntity.ok(messagesResponse);

    }

    @GetMapping("/messages/{id}")
    public ResponseEntity<?> findMessage ( @PathVariable Long id) {
        System.out.println("Notification has been sent");
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setChatModel(chatService.findById(id));
        messageResponse.setStatus(GlobalUtilities.API_SUCCESS_STATUS);
        return ResponseEntity.ok(messageResponse);
//        return ResponseEntity
//                .ok(chatService.findById(id));
    }

}
