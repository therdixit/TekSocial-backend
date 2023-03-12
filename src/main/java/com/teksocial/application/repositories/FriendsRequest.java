package com.teksocial.application.repositories;

import com.teksocial.application.models.FriendsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendsRequest extends JpaRepository< FriendsModel, Long> {
    Optional<FriendsModel> findByFriendRequestSentByIdAndFriendRequestToIdAndDeletedAtIsNull(Long friendSentById, Long friendToId);
    List<FriendsModel> findByFriendRequestToIdAndStatusAndDeletedAtIsNull(Long friendToId, String status);

    List<FriendsModel> findByFriendRequestSentByIdAndStatusAndDeletedAtIsNull(Long friendId, String status);

}
