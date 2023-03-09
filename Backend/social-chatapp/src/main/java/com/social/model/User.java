package com.social.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name", nullable = true, unique = true)
    private String username;

    @Column(name = "first_name",nullable = true)
    private String firstName;

    @Column(name = "last_name",nullable = true)
    private String lastName;

    @Column(name = "email", nullable = true, unique = true)
    private String email;

    @Column(name = "password", nullable = true)
    private String password;

    @Column(name = "confirm_password", nullable = true)
    private String confirmPassword;
    @Column(name = "profile_pic",nullable = true)
    private String profilePic;

    @Column(name = "personal_details",nullable = true)
    private String personalDetails;

    @Column(name = "gender",nullable = true)
    private String gender;

    @Column(name = "contact",nullable = true)
    private String contact;

    public User(String username, String password, String confirmPassword,String email) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.confirmPassword=confirmPassword;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
