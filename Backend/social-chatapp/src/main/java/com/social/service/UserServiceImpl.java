package com.social.service;
import com.social.model.User;
import com.social.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }

}
