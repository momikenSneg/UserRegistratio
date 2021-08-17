package ru.azoft.test.snegireva.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String ID;
    private String login;
    private String password;
    private String FirstName;
    private String LastName;
    private String email;
    private Date registrationDate;
}
