package com.socialserver.socialserver.service;

import com.socialserver.socialserver.modal.Friend;
import com.socialserver.socialserver.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class FriendService {

    @Autowired
    FriendRepository friendRepository;
    public Friend saveFriend(@RequestBody Friend friends)
    {


        return friendRepository.save(friends);
    }
    public List<Friend> getFriend()
    {
        return friendRepository.findAll();
    }

}

