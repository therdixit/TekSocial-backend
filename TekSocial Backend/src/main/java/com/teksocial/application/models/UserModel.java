package com.teksocial.application.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user_profile_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column(name="user_email",  nullable = false)
    private String email;

    @Column(name="user_name")
    private String userName;

    @Column(name="user_profile_pic")
    private String userProfilePic;

    @Column(name="user_created_at", nullable = false)
    private String userCreatedAt;

    @Column(name="user_updated_at")
    private String userUpdatedAt;

    @Column(name="user_deleted_at")
    private String userDeletedAt;

    @Column(name = "user_is_active")
    private Boolean userIsActive;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_contact")
    private String userContact;

    @Column(name = "user_about")
    private String userAbout;

    @Column(name = "user_first_name")
    private String userFirstName;

    @Column(name="user_last_name")
    private String userLastName;

    @Column(name = "user_city")
    private String userCity;

    @Column(name = "user_state")
    private String userState;

    @Column(name = "user_age")
    private String userAge;

}