package com.social.globalexceptionhandler;
import com.social.exception.UserFoundException;
import com.social.exception.UserNotFoundException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<String> userIsPresent(UserFoundException ex) {

        return new ResponseEntity<String> ("User is already present", HttpStatus.CONFLICT) ;
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> usernameNotFoundException(UsernameNotFoundException ex) {

        return new ResponseEntity<String> ("Username not present in DB", HttpStatus.BAD_REQUEST) ;
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> usernNotFoundException(UserNotFoundException ex) {

        return new ResponseEntity<String> ("User not present in DB", HttpStatus.BAD_REQUEST) ;
    }
  @ExceptionHandler(SignatureException.class)
  public ResponseEntity<String> signatureException(SignatureException ex) {

    return new ResponseEntity<String> ("SignatureException Caught", HttpStatus.ACCEPTED) ;
  }
}
