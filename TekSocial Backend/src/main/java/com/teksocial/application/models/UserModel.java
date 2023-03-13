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

    @Column(name="user_email",  nullable = false, unique=true)
//    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
    private String email;

    @Column(name="password", nullable = true)
    private String password;

    @Column(name="confirm_password", nullable = true)
    private String confirm_password;
    @Column(name="user_name",nullable = true)
    private String userName;

    @Column(name="user_profile_pic", nullable = true)
    private String userProfilePic;

    @Column(name="user_created_at",nullable = true)
    private String userCreatedAt;

    @Column(name="user_updated_at",nullable = true)
    private String userUpdatedAt;

    @Column(name="user_deleted_at",nullable = true)
    private String userDeletedAt;

    @Column(name = "user_is_active", nullable = true)
    private Boolean userIsActive;

    @Column(name = "user_gender",nullable = true)
    private String userGender;

    @Column(name = "user_contact",nullable = true)
    private String userContact;

    @Column(name = "user_about",nullable = true)
    private String userAbout;

    @Column(name = "user_first_name",nullable = true)
    private String userFirstName;

    @Column(name="user_last_name",nullable = true)
    private String userLastName;

    @Column(name = "user_city",nullable = true)
    private String userCity;

    @Column(name = "user_state",nullable = true)
    private String userState;

    @Column(name = "user_age",nullable = true)
    private String userAge;

}