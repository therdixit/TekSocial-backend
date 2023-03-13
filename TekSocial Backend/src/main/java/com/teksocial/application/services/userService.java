package com.teksocial.application.services;


import com.teksocial.application.repositories.*;
import com.teksocial.application.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class userService implements UserDetailsService {

    @Autowired
    private User_Repo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userTable = userRepo.findByEmail(email).get();
        return new User(userTable.getEmail(), userTable.getPassword(), new ArrayList<>() );
    }

    private final PasswordEncoder passwordEncoder;


    public userService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public UserModel register(UserModel user) {
    	System.out.println(user.getPassword());
    	System.out.println("hello");
    	System.out.println(user.getConfirm_password());
        if (!Objects.equals(user.getPassword(), user.getConfirm_password()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password do not match");
        UserModel returningData = new UserModel();
        returningData.setUserFirstName(user.getUserFirstName());
        returningData.setUserLastName(user.getUserLastName());
        returningData.setUserName(user.getUserName());
//        returningData.setPersonal_detail(user.getPersonal_detail());
        returningData.setUserGender(user.getUserGender());
        returningData.setId(user.getId());
        returningData.setUserProfilePic(user.getUserProfilePic());
        returningData.setEmail(user.getEmail());
        returningData.setUserIsActive(user.getUserIsActive());
        returningData.setUserUpdatedAt(user.getUserUpdatedAt());
        returningData.setUserCreatedAt(user.getUserCreatedAt());
        returningData.setUserDeletedAt(user.getUserDeletedAt());
        returningData.setUserContact(user.getUserContact());
        returningData.setPassword(passwordEncoder.encode(user.getPassword()));
        returningData.setConfirm_password(passwordEncoder.encode(user.getConfirm_password()));

        return returningData;
    }
}




