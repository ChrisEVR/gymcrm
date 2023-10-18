package com.epam.gymcrm.controllers;

import com.epam.gymcrm.config.AuthEntryPointJwt;
import com.epam.gymcrm.services.UserService;
import com.epam.gymcrm.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Map;
import java.util.logging.Logger;

//@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JWTUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    private static final Logger logger = Logger.getLogger(AuthController.class.getName());

    @GetMapping("/login")
    public ResponseEntity<String> authenticateUser(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "password") String password
    ) {
        logger.info(username + "- - -" + password);
        String token;

        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));

            token = jwtUtil.generateToken(username);
            logger.info("token:" + token);
            logger.info("authentication:" + authentication);
        } catch (Exception e) {
            logger.info("exception:" + e);
            throw new UsernameNotFoundException("Invalid user request!");
        }

        return ResponseEntity.ok(token);
    }

    @PutMapping("/change")
    public ResponseEntity<String> changeLogin(
            @RequestParam(value = "username") String username,
            @RequestParam(value = "oldPassword") String oldPassword,
            @RequestParam(value = "newPassword") String newPassword
    ) {

        userService.updatePassword(username, oldPassword, newPassword);
        return ResponseEntity.ok("Successfully updated!");
    }

}
