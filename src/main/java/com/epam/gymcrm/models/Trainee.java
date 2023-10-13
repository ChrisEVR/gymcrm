package com.epam.gymcrm.models;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.*;

@Entity
@Table(name = "trainee")
@PrimaryKeyJoinColumn(name = "user_id")
@JsonIgnoreProperties(value = {
        "enabled",
        "accountNonExpired",
        "credentialsNonExpired",
        "accountNonLocked",
        "authorities"
})
public class Trainee extends User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "address")
    private String address;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "trainee_trainer",
            joinColumns = @JoinColumn(name = "trainee_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "trainer_id", referencedColumnName = "id")
    )
    private List<Trainer> trainers;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "trainee"
    )
    private List<Training> trainings;

    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    public List<Trainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<Trainer> trainers) {
        this.trainers = trainers;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void addTrainer(Trainer trainer){
        if(this.trainers == null){
            this.trainers = new LinkedList<>();
        }
        this.trainers.add(trainer);
    }

    public void addTraining(Training training){
        if(this.trainings == null){
            this.trainings = new LinkedList<>();
        }

        trainings.add(training);
    }

    @Override
    public String toString() {
        return "Trainee{" +
                "id=" + id +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
//                ", trainers=" + trainers +
                '}' + super.toString();
    }
}
