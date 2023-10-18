package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.logging.Logger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserRepositoryTest {
    private static final Logger logger = Logger.getLogger(UserRepositoryTest.class.getName());
    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Test
    @Order(1)
    public void should_save_user(){
        User user = new User();

        user.setFirstName("test");
        user.setLastName("user");
        user.setUsername("test.user");
        user.setPassword("password");
        user.setActive(true);
        userRepository.save(user);
        User foundUser = userRepository.findByUsername("test.user");
        Assertions.assertEquals(user, foundUser);
    }

    @Test
    @Order(2)
    public void should_return_user(){
        User user = new User(
                "test",
                "user",
                "test.user",
                "password",
                true
        );

        user.setUserId(1L);
        User foundUser = userRepository.findByUsername("test.user");

        Assertions.assertEquals(user, foundUser);
    }

    @Test
    @Order(3)
    public void should_update_user(){
        User user = userRepository.findByUsername("test.user");
        user.setFirstName("New first name");
        user.setLastName("New last name");
        user.setPassword("New password");

        userRepository.save(user);

        User foundUser = userRepository.findByUsername("test.user");

        Assertions.assertEquals(user, foundUser);
    }

    @Test
    @Sql(scripts = "/data.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Order(4)
    public void should_return_list_with_5_users(){
        List<User> userList = userRepository.findByFirstNameAndLastName("test", "test");
//        List<User> userList = userDaoImp.findByFirstnameAndLastname("test", "test");

        Assertions.assertEquals(5, userList.size());
    }
}
