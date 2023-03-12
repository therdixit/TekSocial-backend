package com.teksocial.application.services;

import com.teksocial.application.models.ChatModel;
import com.teksocial.application.repositories.ChatRepository;
import com.teksocial.application.utility.GlobalUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatRepository chatRepository;
    public ChatModel save(ChatModel chatMessage) {
        chatMessage.setStatus(GlobalUtilities.MESSAGE_STATUS_RECEIVED);
        chatRepository.save(chatMessage);
        return chatMessage;
    }

    public Long countNewMessages(Long senderId, Long recipientId) {
        return chatRepository.countBySenderIdAndRecipientIdAndStatus(
                senderId, recipientId, GlobalUtilities.MESSAGE_STATUS_RECEIVED);
    }
    public List<ChatModel> findChatMessages(Long senderId, Long recipientId) {
        var chatId = chatRoomService.getChatId(senderId, recipientId, false);

        var messages =
                chatId.map(cId -> chatRepository.findByChatId(cId)).orElse(new ArrayList<>());

        if(messages.size() > 0) {
            updateStatuses(senderId, recipientId, GlobalUtilities.MESSAGE_STATUS_DELIVERED);
        }

        return messages;
    }
    public ChatModel findById(Long id) {
        return chatRepository
                .findById(id)
                .map(chatMessage -> {
                    chatMessage.setStatus(GlobalUtilities.MESSAGE_STATUS_DELIVERED);
                    return chatRepository.save(chatMessage);
                })
                .orElseThrow(() ->
                        new ResourceNotFoundException("can't find message (" + id + ")"));
    }
    public void updateStatuses(Long senderId, Long recipientId, String status) {
        List<ChatModel> chatModels = chatRepository.findBySenderIdAndRecipientId(senderId, recipientId);
        for(ChatModel chatModel: chatModels){
            chatModel.setStatus(status);
        }
        chatRepository.saveAll(chatModels);
    }

}
