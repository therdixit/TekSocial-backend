package com.teksocial.application.controllers;
import com.teksocial.application.repositories.User_Repo;
import com.teksocial.application.services.AuthRequest;
import com.teksocial.application.services.Token;
import com.teksocial.application.services.jwtResponse;
import com.teksocial.application.services.userService;
import com.teksocial.application.models.User_Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
public class userController {
    private AuthenticationManager authenticationManager;
    @Autowired
    private Token token;

    @Autowired
    private userService user_service;

    private final User_Repo userRepo;

    public userController(AuthenticationManager authenticationManager, userService user_service, Token token, User_Repo userRepo) {
        this.authenticationManager = authenticationManager;
        this.token = token;
        this.user_service = user_service;
        this.userRepo = userRepo;
    }


            @GetMapping("/hello")
            public String hello() {
                return "Hello";
            }

            @PostMapping("/register")
            public User_Table register(@RequestBody User_Table user) {

                User_Table userTable = user_service.register(user);
                userRepo.save(userTable);
                return userTable;
            }

            @PostMapping("/authenticate")

            public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) throws Exception {
                try {
                    authenticate(authRequest.getEmail(), authRequest.getPassword());
                } catch (BadCredentialsException e) {
                    e.printStackTrace();
                    throw new Exception("User not found");
                }


                jwtResponse jwt_new = new jwtResponse();
                jwt_new.setStatus("success");


                UserDetails userDetails = this.user_service.loadUserByUsername(authRequest.getEmail());
                String jwt = token.generateToken(userDetails.getUsername());
                System.out.println("Inside generateToken: " + jwt);
                jwt_new.setJwtToken(jwt);

                return ResponseEntity.ok(jwt_new);
            }

            public void authenticate(String username, String password) throws Exception {
                try {
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
                } catch (DisabledException e) {
                    System.out.println(e.toString());
                    throw new Exception("USER_DISABLED" + e.getMessage());
                } catch (BadCredentialsException e) {
                    System.out.println(e.toString());
                    throw new Exception("INVALID_CREDENTIALS" + e.getMessage());
                }
            }

            @GetMapping("/logout")
            public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
                Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                if (auth != null){
                    new SecurityContextLogoutHandler().logout(request, response, auth);
                }
                return ResponseEntity.ok().build();
            }
        }

