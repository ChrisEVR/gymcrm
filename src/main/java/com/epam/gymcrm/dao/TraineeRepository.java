package com.epam.gymcrm.dao;

import com.epam.gymcrm.models.Trainee;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface TraineeRepository extends JpaRepository<Trainee, Long> {
    Trainee findByUsername(String username);

    List<Trainee> findByFirstNameAndLastName(String firstName, String lastName);

    void deleteByUsername(String username);
}
