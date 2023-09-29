package com.epam.gymcrm.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "training")
@ToString @EqualsAndHashCode
public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private long id;
    @Getter @Setter @Column(name = "trainee_id")
    private long trainee_id;
    @Getter @Setter @Column(name = "trainer_id")
    private long trainer_id;
    @Getter @Setter @Column(name = "training_name")
    private String training_name;
    @Getter @Setter @Column(name = "training_type_id")
    private long training_type_id;
    @Getter @Setter @Column(name = "training_date")
    private Date training_date;
    @Getter @Setter @Column(name = "training_duration")
    private long training_duration;
}
