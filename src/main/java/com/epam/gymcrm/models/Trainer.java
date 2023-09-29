package com.epam.gymcrm.models;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "trainer")
@ToString @EqualsAndHashCode
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private long id;
    @Getter @Setter @Column(name = "specialization")
    private String specialization;
}
