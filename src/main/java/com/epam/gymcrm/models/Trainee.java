package com.epam.gymcrm.models;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "trainee")
@PrimaryKeyJoinColumn(name = "user_id")
public class Trainee extends User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @Column(name = "address")
    private String address;

    public List<TraineeTrainer> getTrainers() {
        return trainers;
    }

    public void setTrainers(List<TraineeTrainer> trainers) {
        this.trainers = trainers;
    }

    @OneToMany
    private List<TraineeTrainer> trainers;
//    @ManyToMany(mappedBy = "trainees")
//    private List<Trainer> trainers;

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
//    public List<Trainer> getTrainers() {
//        return trainers;
//    }
//    public void setTrainers(List<Trainer> trainers) {
//        this.trainers = trainers;
//    }

}
