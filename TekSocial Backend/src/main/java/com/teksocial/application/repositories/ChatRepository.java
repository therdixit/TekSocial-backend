package com.teksocial.application.repositories;

import com.teksocial.application.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ChatRepository extends JpaRepository<Chat,Long> {
    List<Chat> findByUser1OrUser2(String user1,String user2);

}
