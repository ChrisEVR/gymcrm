package com.epam.gymcrm.models;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Entity
@Table(name = "trainee")
@ToString @EqualsAndHashCode
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name = "id")
    private long id;
    @Getter @Setter @Column(name = "date_of_birth")
    private Date date_of_birth;
    @Getter @Setter @Column(name = "address")
    private String address;
}
