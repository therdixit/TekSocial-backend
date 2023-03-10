package com.auth.example.backend.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="public.user_table")
public class User_Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private long user_id;

    @Column(name="first_name",nullable = true)
    private String first_name;

    @Column(name="last_name", nullable = true)
    private String last_name;

    @Column(name="email", nullable = true, unique=true)
    private String email;

    @Column(name="password", nullable = true)
    private String password;

    @Column(name="confirm_password", nullable = true)
    private String confirm_password;

    @Column(name="profile_pic",nullable = true)
    private String profile_pic;

    @Column(name="personal_detail",nullable = true)
    private String personal_detail;

    @Column(name="created_date",nullable = true)
    private Date created_date;

    @Column(name="is_active",nullable = true)
    private Boolean is_active;

    @Column(name="updated_date",nullable = true)
    private Date updated_date;

    @Column(name="gender",nullable = true)
    private String gender;

    @Column(name="contact",nullable = true)
    private String contact;

    @Column(name="deleted_date",nullable = true)
    private Date deleted_date;


}
