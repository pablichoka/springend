package com.kCalControl.model;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="Credentials")
public class Credentials {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Column(name="username", unique = true, nullable = false)
    String username;
    @Column(name="email", unique = true, nullable = false)
    String email;
    @Column(name="password", nullable = false)
    String password;
    @Temporal(TemporalType.TIMESTAMP)
    Date passwordDate;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id")
    private User users;

}
