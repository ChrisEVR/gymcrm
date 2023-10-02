package com.epam.gymcrm.utils;

import com.epam.gymcrm.models.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsernameUtil {
    public String generateUsername(String firstName, String lastName, List<User> listUser){
        StringBuilder username;

        username = new StringBuilder(firstName + "." + lastName);

        if(!listUser.isEmpty()){
            username.append(listUser.size());
        }

        return username.toString();
    }
}
