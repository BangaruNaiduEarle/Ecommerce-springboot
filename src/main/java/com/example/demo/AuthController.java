package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.configuration.JwtHelper;
import com.example.demo.dto.CredentialsDto;
import com.example.demo.dto.UserDto;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JwtHelper helper;

    private Logger logger =  LoggerFactory.getLogger(AuthController.class);


//    @PostMapping("/auth/login")
//    public ResponseEntity<?> login(@RequestBody @Valid CredentialsDto credentialsDto) {
//
//        this.doAuthenticate(credentialsDto.getEmail(), credentialsDto.getPassword());
//
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(credentialsDto.getEmail());
//        String token = this.helper.generateToken(userDetails);
//        UserDto userDto = new UserDto();
//        userDto.setToken(token);
//        userDto.setUserName(userDetails.getUsername());
////        JwtResponse response = JwtResponse.builder()
////                .jwtToken(token)
////                .username(userDetails.getUsername()).build();
//        return new ResponseEntity<>(userDto, HttpStatus.OK);
//    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
