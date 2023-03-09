package com.social.service;
import com.social.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void saveUser(User user);

    User findByUserName(String userName);
}
