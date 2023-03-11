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
        User_Table userTable = userRepo.findByEmail(email).get();
        return new User(userTable.getEmail(), userTable.getPassword(), new ArrayList<>() );
    }

    private final PasswordEncoder passwordEncoder;


    public userService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User_Table register(User_Table user) {
    	System.out.println(user.getPassword());
    	System.out.println("hello");
    	System.out.println(user.getConfirm_password());
        if (!Objects.equals(user.getPassword(), user.getConfirm_password()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Password do not match");
        User_Table returningData = new User_Table();
        returningData.setFirst_name(user.getFirst_name());
        returningData.setLast_name(user.getLast_name());
        returningData.setPersonal_detail(user.getPersonal_detail());
        returningData.setGender(user.getGender());
        returningData.setUser_id(user.getUser_id());
        returningData.setProfile_pic(user.getProfile_pic());
        returningData.setEmail(user.getEmail());
        returningData.setIs_active(user.getIs_active());
        returningData.setUpdated_date(user.getUpdated_date());
        returningData.setDeleted_date(user.getDeleted_date());
        returningData.setCreated_date(user.getCreated_date());
        returningData.setContact(user.getContact());
        returningData.setPassword(passwordEncoder.encode(user.getPassword()));
        returningData.setConfirm_password(passwordEncoder.encode(user.getConfirm_password()));

        return returningData;
    }
}




