package com.socialserver.socialserver.controller;

import com.socialserver.socialserver.modal.Friend;
import com.socialserver.socialserver.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {


    @Autowired
    FriendService friendService;


    @PostMapping("/friends")
    public Friend addFriend(@RequestBody Friend friends)
    {
        return friendService.saveFriend(friends);
    }

    @GetMapping("/findfriend")
    public List<Friend> getFriends()
    {
        return friendService.getFriend();
    }

}

