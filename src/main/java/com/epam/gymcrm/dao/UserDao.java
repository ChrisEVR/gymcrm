package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainee;
import com.epam.gymcrm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
}
