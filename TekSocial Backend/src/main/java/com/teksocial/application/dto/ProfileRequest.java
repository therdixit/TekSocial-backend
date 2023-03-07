package com.teksocial.application.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileRequest {
    private Long id;
    private String email;
    private String userName;
    private String firstName;
    private String lastName;
    private String userContact;
    private String userAbout;
    private String age;
    private String city;
    private String state;

}
