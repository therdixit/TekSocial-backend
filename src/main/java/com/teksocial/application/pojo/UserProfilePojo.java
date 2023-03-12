package com.teksocial.application.pojo;

import java.sql.Blob;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfilePojo {
    private String profilePic;
    private String profileName;
    private Long profileId;
}
