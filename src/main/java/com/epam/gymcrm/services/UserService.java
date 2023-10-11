package com.epam.gymcrm.services;

import com.epam.gymcrm.dao.TraineeDaoImp;
import com.epam.gymcrm.dao.UserDaoImp;
import com.epam.gymcrm.models.User;
import com.epam.gymcrm.utils.PasswordUtil;
import com.epam.gymcrm.utils.UsernameUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    UserDaoImp userDaoImp;
    @Autowired
    private PasswordUtil passwordUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(
            String firstname,
            String lastname
    ){
        User user = new User();

        user.setFirstName(firstname);
        user.setLastName(lastname);
        user.setUsername("username");
        user.setPassword(passwordEncoder.encode(passwordUtil.generatePassword()));
        user.setActive(true);
        userDaoImp.createUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDaoImp.loadByUsername(username);
    }
}
