package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.UserRepository;
import com.epam.gymcrm.models.User;
import com.epam.gymcrm.utils.PasswordUtil;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordUtil passwordUtil;

    public UserService(UserRepository userRepository,
                       PasswordUtil passwordUtil
    ) {
        this.userRepository = userRepository;
        this.passwordUtil = passwordUtil;
    }

    public void updatePassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findByUsername(username);

        if (new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
            user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
            userRepository.save(user);

        } else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }
}
