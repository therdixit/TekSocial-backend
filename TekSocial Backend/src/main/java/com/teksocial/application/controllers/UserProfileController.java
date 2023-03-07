package com.teksocial.application.controllers;

import com.teksocial.application.dto.ProfileRequest;
import com.teksocial.application.dto.ProfileResponse;
import com.teksocial.application.dto.UserUpdateResponse;
import com.teksocial.application.models.UserModel;
import com.teksocial.application.repositories.UserRepository;
import com.teksocial.application.utility.GlobalUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
    ResponseEntity<?> getProfileDetails(@PathVariable Long id){
        Optional<UserModel> userModel = userRepository.findById(id);
        ProfileResponse profileResponse = new ProfileResponse();
        if(userModel.isPresent()){
            UserModel userModel1 = userModel.get();
            profileResponse.setAge(userModel1.getUserAge());
            profileResponse.setUserAbout(userModel1.getUserAbout());
            profileResponse.setCity(userModel1.getUserCity());
            profileResponse.setState(userModel1.getUserState());
            profileResponse.setUserContact(userModel1.getUserContact());
            profileResponse.setUserName(userModel1.getUserName());
            profileResponse.setFirstName(userModel1.getUserFirstName());
            profileResponse.setLastName(userModel1.getUserLastName());
            profileResponse.setUserEmail(userModel1.getEmail());
            profileResponse.setStatus(GlobalUtilities.API_SUCCESS_STATUS);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(profileResponse);
        }else {
            profileResponse.setStatus(GlobalUtilities.API_FAILURE_STATUS);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(profileResponse);
        }
    }
    @RequestMapping(value = "/profile" , method = RequestMethod.POST)
    public ResponseEntity<?>  updateProfile(@RequestBody ProfileRequest profileRequest){
        System.out.println();
        System.out.println(profileRequest);
        System.out.println();
        UserModel userModel = userRepository.findById(profileRequest.getId()).get();
        userModel.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
        userModel.setEmail(profileRequest.getEmail());
        userModel.setUserName(profileRequest.getUserName());
        userModel.setUserFirstName(profileRequest.getFirstName());
        userModel.setUserLastName(profileRequest.getLastName());
        userModel.setUserAbout(profileRequest.getUserAbout());
        userModel.setUserAge(profileRequest.getAge());
        userModel.setUserCity(profileRequest.getCity());
        userModel.setUserState(profileRequest.getState());
        userModel.setUserContact(profileRequest.getUserContact());
        userRepository.save(userModel);
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse("success", "user updated successfully");
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(userUpdateResponse);
    }
}
