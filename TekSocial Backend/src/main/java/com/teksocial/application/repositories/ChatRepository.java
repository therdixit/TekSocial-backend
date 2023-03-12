package com.teksocial.application.repositories;

import com.teksocial.application.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<ChatModel,Long> {
    Long countBySenderIdAndRecipientIdAndStatus(Long senderId, Long recipientId, String status);
    List<ChatModel> findByChatId(String id);

   List<ChatModel> findBySenderIdAndRecipientId(Long senderId , Long recipientId);
}
