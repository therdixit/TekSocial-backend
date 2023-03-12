package com.teksocial.application.controllers;
import com.teksocial.application.dto.RegisterResponse;
import com.teksocial.application.repositories.UserRepository;
import com.teksocial.application.repositories.User_Repo;
import com.teksocial.application.services.AuthRequest;
import com.teksocial.application.services.Token;
import com.teksocial.application.services.jwtResponse;
import com.teksocial.application.services.userService;
import com.teksocial.application.models.UserModel;
import com.teksocial.application.utility.GlobalUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin(origins = "*")
public class userController {
	
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private Token token;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

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
            public ResponseEntity<?> register(@RequestBody UserModel user) {

                RegisterResponse registerResponse = new RegisterResponse();
                Optional<UserModel> userInDB = userRepository.findByEmail(user.getEmail());
                if(userInDB.isPresent())
                {
                    registerResponse.setStatus(GlobalUtilities.API_FAILURE_STATUS);
                    registerResponse.setMessage("User is present in DB");
                }
                else {
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                        user.setConfirm_password(passwordEncoder.encode(user.getConfirm_password()));
                        registerResponse.setStatus(GlobalUtilities.API_SUCCESS_STATUS);
                        registerResponse.setMessage("User not present");
                        userRepository.save(user);
                }

                return ResponseEntity.ok(registerResponse);
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
                Optional<UserModel> loggedUser = this.userRepo.findByEmail(authRequest.getEmail());
                if (loggedUser.isPresent()) {
                    Long userId = loggedUser.get().getId();
                    jwt_new.setId(userId);
                } else {
                	System.out.println("no user found");
                }
                
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
                	System.out.println();
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

