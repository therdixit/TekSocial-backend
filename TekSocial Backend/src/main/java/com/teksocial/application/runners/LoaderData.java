//package com.teksocial.application.runners;
//
//import com.teksocial.application.models.FriendsModel;
//import com.teksocial.application.models.UserModel;
//import com.teksocial.application.repositories.FriendsRequest;
//import com.teksocial.application.repositories.UserRepository;
//import com.teksocial.application.utility.GlobalUtilities;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Component
//public class LoaderData implements ApplicationRunner {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private FriendsRequest friendsRequest;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        System.out.println("Adding data to Database");
//        UserModel userModel1 = new UserModel();
//        userModel1.setEmail("richa@gmail.com");
//        userModel1.setUserAbout("Passionate About Life");
//        userModel1.setUserCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
//        userModel1.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
//        userModel1.setUserAge("23");
//        userModel1.setUserCity("Gwalior");
//        userModel1.setUserContact("9876543257");
//        userModel1.setUserFirstName("Richa");
//        userModel1.setUserLastName("Agarwal");
//        userModel1.setUserName("richa@123");
//        userModel1.setUserGender(GlobalUtilities.FEMALE_GENDER);
//        userModel1.setUserState("Madhya Pradesh");
//        userRepository.save(userModel1);
//
//        System.out.println();
//        System.out.println();
//        System.out.println("ADDED RICHA To DATABASE");
//        System.out.println();
//        System.out.println();
//        
//        
//        UserModel userModel2 = new UserModel();
//        userModel2.setEmail("ritik@gmail.com");
//        userModel2.setUserAbout("I am Ritik");
//        userModel2.setUserCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
//        userModel2.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
//        userModel2.setUserAge("23");
//        userModel2.setUserCity("Gwalior");
//        userModel2.setUserContact("9875446897");
//        userModel2.setUserFirstName("Ritik");
//        userModel2.setUserLastName("Agrawal");
//        userModel2.setUserName("ritik@1245");
//        userModel2.setUserGender(GlobalUtilities.MALE_GENDER);
//        userModel2.setUserState("Madhya Pradesh");
//        userRepository.save(userModel2);
//        System.out.println();
//        System.out.println();
//        System.out.println("ADDED RITIK TO DATABASE");
//        System.out.println();
//        System.out.println();
//        
//        
//        UserModel userModel3 = new UserModel();
//        userModel3.setEmail("madhav@gmail.com");
//        userModel3.setUserAbout("I am Maddy");
//        userModel3.setUserCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
//        userModel3.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
//        userModel3.setUserAge("23");
//        userModel3.setUserCity("Gwalior");
//        userModel3.setUserContact("9876543823");
//        userModel3.setUserFirstName("Madhav");
//        userModel3.setUserLastName("agrawal");
//        userModel3.setUserName("madhav@1233");
//        userModel3.setUserGender(GlobalUtilities.MALE_GENDER);
//        userModel3.setUserState("Madhya Pradesh");
//        userRepository.save(userModel3);
//        System.out.println();
//        System.out.println();
//        System.out.println("ADDED MADHAV TO DATABASE");
//        System.out.println();
//        System.out.println();
//        
////        UserModel userModel4 = new UserModel();
////        userModel3.setEmail("sanya@gmail.com");
////        userModel3.setUserAbout("I am sanya");
////        userModel3.setUserCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
////        userModel3.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
////        userModel3.setUserAge("23");
////        userModel3.setUserCity("Gwalior");
////        userModel3.setUserContact("9876543833");
////        userModel3.setUserFirstName("Sanya");
////        userModel3.setUserLastName("agrawal");
////        userModel3.setUserName("sanya@1233");
////        userModel3.setUserGender(GlobalUtilities.FEMALE_GENDER);
////        userModel3.setUserState("Madhya Pradesh");
////        userRepository.save(userModel4);
////        System.out.println();
////        System.out.println();
////        System.out.println("ADDED SANYA TO DATABASE");
////        System.out.println();
////        System.out.println();
////        
////        UserModel userModel5 = new UserModel();
////        userModel3.setEmail("rishabh@gmail.com");
////        userModel3.setUserAbout("I am Rishabh");
////        userModel3.setUserCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
////        userModel3.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
////        userModel3.setUserAge("23");
////        userModel3.setUserCity("Gwalior");
////        userModel3.setUserContact("9876543673");
////        userModel3.setUserFirstName("Rishabh");
////        userModel3.setUserLastName("agrawal");
////        userModel3.setUserName("Rishabh@1233");
////        userModel3.setUserGender(GlobalUtilities.MALE_GENDER);
////        userModel3.setUserState("Madhya Pradesh");
////        userRepository.save(userModel5);
////        System.out.println();
////        System.out.println();
////        System.out.println("ADDED RISHABH TO DATABASE");
////        System.out.println();
////        System.out.println();
////        
////        UserModel userModel6 = new UserModel();
////        userModel3.setEmail("Shahrukh@gmail.com");
////        userModel3.setUserAbout("I am Shahrukh");
////        userModel3.setUserCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
////        userModel3.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
////        userModel3.setUserAge("23");
////        userModel3.setUserCity("Gwalior");
////        userModel3.setUserContact("98765437893");
////        userModel3.setUserFirstName("Shahrukh");
////        userModel3.setUserLastName("agrawal");
////        userModel3.setUserName("Shahrukh@1233");
////        userModel3.setUserGender(GlobalUtilities.MALE_GENDER);
////        userModel3.setUserState("Madhya Pradesh");
////        userRepository.save(userModel6);
////        System.out.println();
////        System.out.println();
////        System.out.println("ADDED SHAHRUKH TO DATABASE");
////        System.out.println();
////        System.out.println();
////        
////        UserModel userModel7 = new UserModel();
////        userModel3.setEmail("Siddharth@gmail.com");
////        userModel3.setUserAbout("I am Siddharth");
////        userModel3.setUserCreatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
////        userModel3.setUserUpdatedAt(GlobalUtilities.formatDateAndTime(LocalDateTime.now()));
////        userModel3.setUserAge("23");
////        userModel3.setUserCity("Gwalior");
////        userModel3.setUserContact("9876556823");
////        userModel3.setUserFirstName("siddharth");
////        userModel3.setUserLastName("agrawal");
////        userModel3.setUserName("siddharth@1233");
////        userModel3.setUserGender(GlobalUtilities.MALE_GENDER);
////        userModel3.setUserState("Madhya Pradesh");
////        userRepository.save(userModel7);
////        System.out.println();
////        System.out.println();
////        System.out.println("ADDED SIDDHARTHA TO DATABASE");
////        System.out.println();
////        System.out.println();
//        
//        
////        FriendsModel friendsModel = new FriendsModel();
////        friendsModel.setFriendRequestToId(1L);
////        friendsModel.setFriendRequestSentById(2L);
////        friendsModel.setStatus(GlobalUtilities.FRIEND_PENDING_REQUEST);
////        friendsRequest.save(friendsModel);
//
//
//
//    }
//}
