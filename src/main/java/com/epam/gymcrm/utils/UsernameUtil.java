package com.epam.gymcrm.utils;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsernameUtil {
    public <T> String generateUsername(String firstName, String lastName, List<T> listUser) {
        StringBuilder username;

        username = new StringBuilder(firstName + "." + lastName);

        if (!listUser.isEmpty()) {
            username.append(listUser.size());
        }

        return username.toString();
    }
}
