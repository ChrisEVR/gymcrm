package com.epam.gymcrm.controllers;

import com.epam.gymcrm.models.User;
//import com.epam.gymcrm.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {
    //@Autowired
    //private JWTUtil jwtUtil;

    //@Autowired
    //private AuthenticationManager authenticationManagerBean;

//    @PostMapping("/login")
//    public String authenticateUser(@RequestBody User user){
//    Authentication authentication = authenticationManagerBean.authenticate(
//            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
//        );

//        if(!authentication.isAuthenticated()){
//            throw new UsernameNotFoundException("Invalid user request");
//        }

//        return jwtUtil.generateToken(user.getUsername());
//    }
}
