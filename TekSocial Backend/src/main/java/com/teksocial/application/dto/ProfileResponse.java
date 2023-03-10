package com.teksocial.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileResponse {
    private String status;
    private String firstName;
    private String lastName;
    private String userEmail;
    private String userContact;
    private String age;
    private String city;
    private String state;
    private String userName;
    private String userAbout;
    private String profilePic;
}
