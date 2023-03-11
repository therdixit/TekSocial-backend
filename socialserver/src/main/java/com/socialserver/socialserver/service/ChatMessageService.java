package com.socialserver.socialserver.service;

import com.socialserver.socialserver.modal.ChatMessage;
import com.socialserver.socialserver.modal.Message;
import com.socialserver.socialserver.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatMessageService {
    @Autowired
    private ChatMessageRepository chatMessageRepository;

    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    public List<ChatMessage> findBySenderId(Long senderId) {
        return chatMessageRepository.findBySenderId(senderId);
    }

    public List<ChatMessage> findByRecipientId(Long recipientId) {
        return chatMessageRepository.findByRecipientId(recipientId);
    }
}
