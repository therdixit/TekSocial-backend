package com.teksocial.application.repositories;

import com.teksocial.application.models.ChatRoomModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomModel, Long> {
    Optional<ChatRoomModel> findBySenderIdAndRecipientId(Long senderId, Long recipientId);
}
