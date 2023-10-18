package com.epam.gymcrm.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.springframework.boot.actuate.endpoint.web.Link;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@Table(name = "trainer")
@PrimaryKeyJoinColumn(name = "user_id")
@JsonIgnoreProperties(value = {
        "enabled",
        "accountNonExpired",
        "credentialsNonExpired",
        "accountNonLocked",
        "authorities"
})
public class Trainer extends User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "specialization", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TrainingType trainingType;

    @ManyToMany(mappedBy = "trainers",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonIgnore
    private List<Trainee> trainees;

    @OneToMany(mappedBy = "trainer")
    private List<Training> trainings;

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }


    public TrainingType getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(TrainingType trainingType) {
        this.trainingType = trainingType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Trainee> getTrainees() {
        return trainees;
    }

    public void setTrainees(List<Trainee> trainees) {
        this.trainees = trainees;
    }

    public void addTrainee(Trainee trainee) {
        if (this.trainees == null) {
            this.trainees = new LinkedList<>();
        }
        trainees.add(trainee);
    }

    public void addTraining(Training training) {
        if (this.trainings == null) {
            this.trainings = new LinkedList<>();
        }

        trainings.add(training);
    }

    public void removeTrainee(Trainee trainee) {
        trainees.remove(trainee);
    }

    @Override
    public String toString() {
        return "Trainer{" +
//                "id=" + id +
                ", trainingType=" + trainingType +
                '}';
    }
}
