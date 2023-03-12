package com.teksocial.application.controllers;

import com.cloudinary.utils.ObjectUtils;
import com.teksocial.application.dto.*;
import com.teksocial.application.models.FriendsModel;
import com.teksocial.application.models.UserModel;
import com.teksocial.application.pojo.UserProfilePojo;
import com.teksocial.application.repositories.FriendsRequest;
import com.teksocial.application.repositories.UserRepository;
import com.teksocial.application.services.CloudinaryService;
import com.teksocial.application.utility.GlobalUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Objects.isNull;

@RestController
@CrossOrigin(origins="*")
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendsRequest friendsRequest;

    @Autowired
    private CloudinaryService cloudinaryService;

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
            profileResponse.setProfilePic(userModel1.getUserProfilePic());
            profileResponse.setStatus(GlobalUtilities.API_SUCCESS_STATUS);
            System.out.println();
            System.out.println(profileResponse);
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

    @RequestMapping(value = "/getallusers", method = RequestMethod.GET)
    public ResponseEntity<?> getAllUsersWithSearchKey(@RequestParam(name = "searchkey") String searchKey){
        if(!isNull(searchKey)){
            List<UserModel> userModelList =  userRepository.findAll();
            ArrayList<UserProfilePojo> userProfilePojos = new ArrayList<>();
            for(UserModel userModel : userModelList){
                userProfilePojos.add(new UserProfilePojo(userModel.getUserProfilePic(), userModel.getUserName(), userModel.getId()));
            }
            GetAllProfileResponse getAllProfileResponse = new GetAllProfileResponse();
            getAllProfileResponse.setProfileList(userProfilePojos);
            getAllProfileResponse.setStatus(GlobalUtilities.API_SUCCESS_STATUS);
            return  ResponseEntity.status(HttpStatus.ACCEPTED).body(getAllProfileResponse);
        }
        else {
            GetAllProfileResponse getAllProfileResponse = new GetAllProfileResponse();
            getAllProfileResponse.setStatus(GlobalUtilities.API_SUCCESS_STATUS);
            getAllProfileResponse.setProfileList(new ArrayList<UserProfilePojo>());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(getAllProfileResponse);
        }
    }
    @RequestMapping(value = "/getallrequests/{id}", method = RequestMethod.GET)
    public  ResponseEntity<?> getAllRequestsForAUser(@PathVariable Long id){
        List<FriendsModel> friendsModels = friendsRequest.findByFriendRequestToIdAndStatusAndDeletedAtIsNull(id, GlobalUtilities.FRIEND_PENDING_REQUEST);
        ArrayList<UserProfilePojo> userProfilePojos = new ArrayList<>();
        for(FriendsModel friendsModel: friendsModels){
            UserModel userModel = userRepository.findById(friendsModel.getFriendRequestSentById()).get();
            userProfilePojos.add(new UserProfilePojo(userModel.getUserProfilePic(), userModel.getUserName(), userModel.getId()));
        }
        GetAllProfileResponse getAllProfileResponse = new GetAllProfileResponse(GlobalUtilities.API_SUCCESS_STATUS, userProfilePojos);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(getAllProfileResponse);
    }
    @RequestMapping(value = "/sendrequest/{profileId}/{userId}", method = RequestMethod.POST)
    public ResponseEntity<?> sendRequest(@PathVariable Long profileId, @PathVariable Long userId){
        SendFriendRequestResponse sendFriendRequestResponse = new SendFriendRequestResponse();
        Optional<FriendsModel> friendsModel = friendsRequest.findByFriendRequestSentByIdAndFriendRequestToIdAndDeletedAtIsNull(profileId, userId);
        if(friendsModel.isPresent()){
            sendFriendRequestResponse.setStatus(GlobalUtilities.API_FAILURE_STATUS);
            sendFriendRequestResponse.setMessage("This relation has been existed");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(sendFriendRequestResponse);
        }
        Optional<FriendsModel> friendsModel1 = friendsRequest.findByFriendRequestSentByIdAndFriendRequestToIdAndDeletedAtIsNull(userId, profileId);
        if(friendsModel1.isPresent()){
            sendFriendRequestResponse.setStatus(GlobalUtilities.API_FAILURE_STATUS);
            sendFriendRequestResponse.setMessage("This relation has been existed");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(sendFriendRequestResponse);
        }
        FriendsModel friendsModel2 = new FriendsModel();
        friendsModel2.setFriendRequestToId(profileId);
        friendsModel2.setFriendRequestSentById(userId);
        friendsModel2.setCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
        friendsModel2.setUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
        friendsModel2.setStatus(GlobalUtilities.FRIEND_PENDING_REQUEST);
        friendsRequest.save(friendsModel2);
        sendFriendRequestResponse.setStatus(GlobalUtilities.API_SUCCESS_STATUS);
        sendFriendRequestResponse.setMessage("Friend request accepted");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(sendFriendRequestResponse);
    }
    @RequestMapping(value = "/acceptrequest/{profileId}/{userId}", method = RequestMethod.POST)
    public ResponseEntity<?> acceptFriendRequest(@PathVariable Long profileId, @PathVariable Long userId){
    FriendsModel friendsModel = friendsRequest.findByFriendRequestSentByIdAndFriendRequestToIdAndDeletedAtIsNull(profileId, userId).get();
    friendsModel.setStatus(GlobalUtilities.FRIEND_ACCEPTED_REQUEST);
    friendsRequest.save(friendsModel);
        SendFriendRequestResponse sendFriendRequestResponse = new SendFriendRequestResponse(GlobalUtilities.API_SUCCESS_STATUS, "Friend request accepted");
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(sendFriendRequestResponse);
    }
    @RequestMapping(value = "/getallfriends/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllFriends(@PathVariable Long id){
        ArrayList<UserProfilePojo> userProfilePojos = new ArrayList<>();
        List<FriendsModel> friendsModels1 = friendsRequest.findByFriendRequestSentByIdAndStatusAndDeletedAtIsNull(id, GlobalUtilities.FRIEND_ACCEPTED_REQUEST);
        List<FriendsModel> friendsModels2 = friendsRequest.findByFriendRequestToIdAndStatusAndDeletedAtIsNull(id , GlobalUtilities.FRIEND_ACCEPTED_REQUEST);
        for(FriendsModel friend: friendsModels1){
            UserModel userModel = userRepository.findById(friend.getFriendRequestToId()).get();
            UserProfilePojo userProfilePojo = new UserProfilePojo(userModel.getUserProfilePic(), userModel.getUserName(), userModel.getId());
            userProfilePojos.add(userProfilePojo);
        }
        for(FriendsModel friend: friendsModels2){
            UserModel userModel = userRepository.findById(friend.getFriendRequestSentById()).get();
            UserProfilePojo userProfilePojo = new UserProfilePojo(userModel.getUserProfilePic(), userModel.getUserName(), userModel.getId());
            userProfilePojos.add(userProfilePojo);
        }
        GetAllProfileResponse getAllProfileResponse = new GetAllProfileResponse(GlobalUtilities.API_SUCCESS_STATUS, userProfilePojos);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(getAllProfileResponse);
    }
    @RequestMapping(value = "/deletefriendrequest/{userId}/{profileId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteFriendRequest(@PathVariable Long userId, @PathVariable Long profileId){
        FriendsModel friendsModel = null;
        Optional<FriendsModel> friendsModel1 = friendsRequest.findByFriendRequestSentByIdAndFriendRequestToIdAndDeletedAtIsNull(userId, profileId);
        if(friendsModel1.isPresent()){
            friendsModel = friendsModel1.get();
            friendsModel.setDeletedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
            friendsRequest.save(friendsModel);
        }
        Optional<FriendsModel> friendsModel2 = friendsRequest.findByFriendRequestSentByIdAndFriendRequestToIdAndDeletedAtIsNull(profileId, userId);
        if(friendsModel2.isPresent()){
            friendsModel = friendsModel2.get();
            friendsModel.setDeletedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
            friendsRequest.save(friendsModel);
        }
        UserUpdateResponse userUpdateResponse = new UserUpdateResponse("success", "Deleted successfully");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userUpdateResponse);

    }
    @RequestMapping(value = "/uploadprofileimage", method = RequestMethod.POST)
    ResponseEntity<?> uploadProfileImage(@RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) throws IOException {
        UploadFileResponse uploadFileResponse = new UploadFileResponse();
        try {
            UserModel userModel = userRepository.findById(userId).get();
            Map results = cloudinaryService.upload(file.getBytes(), ObjectUtils.asMap("resourcetype", "auto"));
            userModel.setUserProfilePic(results.get("url").toString());
            userRepository.save(userModel);
            uploadFileResponse.setStatus(GlobalUtilities.API_SUCCESS_STATUS);
            uploadFileResponse.setData(results.get("url").toString());
            System.out.println("success");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(uploadFileResponse);
        }catch (Exception e){
            e.printStackTrace();
            uploadFileResponse.setStatus(GlobalUtilities.API_FAILURE_STATUS);
            uploadFileResponse.setData("Something went to hell");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(uploadFileResponse);
        }

    }
}
