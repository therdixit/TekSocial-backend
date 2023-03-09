package com.social.controller;
import com.social.config.JwtUtils;
import com.social.exception.UserNotFoundException;
import com.social.model.JwtRequest;
import com.social.model.JwtResponse;
import com.social.model.User;
import com.social.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class AuthenticateController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;


    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {
            System.out.println("Inside generateToken 1");
            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
            System.out.println("Inside generateToken 2 ");


        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found ");
        }

        //authenticate

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        System.out.println("Inside generateToken "+token);

        return ResponseEntity.ok(new JwtResponse(token));


    }


    private void authenticate(String username, String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            System.out.println("Inside authenticate ");

        } catch (DisabledException e) {
            throw new Exception("USER DISABLED " + e.getMessage());
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid Credentials " + e.getMessage());
        }
    }


}

