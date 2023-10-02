package com.epam.gymcrm.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class PasswordUtil {
    private static final int STRING_LENGTH = 10;
    public String generatePassword(){
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for(int i = 0; i < STRING_LENGTH; ++i){
            int randomChar = random.nextInt(95) + 33;
            password.append((char) randomChar);
        }

        return password.toString();
    }
}
