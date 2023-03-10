package com.teksocial.application.controllers;

import com.teksocial.application.services.*;
import com.teksocial.application.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;
//    @PostMapping("/friends")
//    public Friends addFriend(@RequestBody Friends friends)
//    {
//        return friendService.saveFriend(friends);
//    }

    @GetMapping("/findfriend")
    public List<Friends> getFriends()
    {
        return friendService.getFriend();
    }

}
