package ru.azoft.test.snegireva.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registered_user")
public class User implements Serializable {
    @Id
    @Column(name="id", nullable = false, updatable = false)
    private String ID;
    @Column(name="login", nullable = false)
    private String login;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="firstName", nullable = false)
    private String firstName;
    @Column(name="lastName", nullable = false)
    private String lastName;
    @Column(name="email", nullable = false)
    private String email;
    @Column(name="registrationDate", nullable = false)
    private Date registrationDate;
}
