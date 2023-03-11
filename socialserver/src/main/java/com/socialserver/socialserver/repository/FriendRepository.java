package com.socialserver.socialserver.repository;

import com.socialserver.socialserver.modal.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend,Integer> {


}