package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainer;
import com.epam.gymcrm.models.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class UserDaoImp implements UserDao {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<User> findByFirstnameAndLastname(String firstname, String lastname){
        String queryString = "SELECT u FROM User u WHERE u.firstName = :firstname AND u.lastName = :lastname";
        TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
        query
                .setParameter("firstname", firstname)
                .setParameter("lastname", lastname);

        return query.getResultList();
    };

    @Override
    public User loadByUsername(String username){
        String queryString = "SELECT u FROM User u WHERE u.username = :username";
        TypedQuery<User> query = entityManager.createQuery(queryString, User.class);
        query.setParameter("username", username);

        return query.getSingleResult();
    }

    public void createUser(User user){
        entityManager.persist(user);
    }
}
