package com.teksocial.application.repositories;

import com.teksocial.application.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friends,Integer> {

}
