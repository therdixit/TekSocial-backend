package com.teksocial.application.services;

import com.teksocial.application.models.*;
import com.teksocial.application.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    @Autowired
    private FriendRepository friendRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public Friends saveFriend(Long profileId)
    {
    	Friends newFriends = new Friends();
    	  	
    	Optional<UserModel> user = userRepository.findById(profileId);

    	if (user.isPresent()) {
    	    UserModel newUser = new UserModel();
    	    UserModel existingUser = user.get();
    	    newUser.setUserName(existingUser.getUserName());
    	    newUser.setUserAbout(existingUser.getUserAbout());
    	
    	    newFriends.setId(profileId);
    	    newFriends.setName(newUser.getUserName());
    	    newFriends.setDetails(newUser.getUserAbout());
    	    newFriends.setStatus(true);
    	    
    	}
    	
        return friendRepository.save(newFriends);
    }
    public List<Friends> getFriend()
    {
        return friendRepository.findAll();
    }

}
