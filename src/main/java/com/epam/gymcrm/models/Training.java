package com.epam.gymcrm.models;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "training")
@ToString @EqualsAndHashCode
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "trainee_id")
    private Long trainee_id;
    @Column(name = "trainer_id")
    private Long trainer_id;
    @Column(name = "training_name")
    private String training_name;
    @Column(name = "training_type_id")
    private Long training_type_id;
    @Column(name = "training_date")
    private Date training_date;
    @Column(name = "training_duration")
    private Long training_duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTrainee_id() {
        return trainee_id;
    }

    public void setTrainee_id(Long trainee_id) {
        this.trainee_id = trainee_id;
    }

    public Long getTrainer_id() {
        return trainer_id;
    }

    public void setTrainer_id(Long trainer_id) {
        this.trainer_id = trainer_id;
    }

    public String getTraining_name() {
        return training_name;
    }

    public void setTraining_name(String training_name) {
        this.training_name = training_name;
    }

    public Long getTraining_type_id() {
        return training_type_id;
    }

    public void setTraining_type_id(Long training_type_id) {
        this.training_type_id = training_type_id;
    }

    public Date getTraining_date() {
        return training_date;
    }

    public void setTraining_date(Date training_date) {
        this.training_date = training_date;
    }

    public Long getTraining_duration() {
        return training_duration;
    }

    public void setTraining_duration(Long training_duration) {
        this.training_duration = training_duration;
    }
}
