package com.teksocial.application.services;

import com.teksocial.application.models.*;
import com.teksocial.application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FriendService {
    @Autowired
    private FriendRepository friendRepository;
    public Friends saveFriend(Friends friends)
    {
        return friendRepository.save(friends);
    }
    public List<Friends> getFriend()
    {
        return friendRepository.findAll();
    }

}
