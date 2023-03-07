package com.teksocial.application.runners;

import com.teksocial.application.models.UserModel;
import com.teksocial.application.repositories.UserRepository;
import com.teksocial.application.utility.GlobalUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LoaderData implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("Adding data to Database");
        UserModel userModel = new UserModel();
        userModel.setEmail("richa@gmail.com");
        userModel.setUserAbout("Teksystem");
        userModel.setUserCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
        userModel.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
        userModel.setUserAge("23");
        userModel.setUserCity("Gwalior");
        userModel.setUserContact("123456789");
        userModel.setUserFirstName("Richa");
        userModel.setUserLastName("Agarwal");
        userModel.setUserName("richa@123");
        userModel.setUserGender(GlobalUtilities.FEMALE_GENDER);
        userModel.setUserState("Madhya Pradesh");
        userRepository.save(userModel);
        System.out.println("ADDED TO THE DATABASE");

    }
}
