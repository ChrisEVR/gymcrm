package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByFirstNameAndLastName(String firstName, String lastName);
    User findByUsername(String username);
}
