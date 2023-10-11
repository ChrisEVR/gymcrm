package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserDao  {
    List<User> findByFirstnameAndLastname(String firstName, String lastName);
    User loadByUsername(String username);
}
